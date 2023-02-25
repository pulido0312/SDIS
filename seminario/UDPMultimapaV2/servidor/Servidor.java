package protocol.servidor;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.HashMap;

import distribuidos.mapqueue.MultiMap;
import protocol.common.MalMensajeProtocoloException;
import protocol.common.MensajeProtocolo;
import protocol.common.Primitiva;
import protocol.common.UDPHelper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class Servidor implements Runnable {
  public final int PUERTO ;
  public final int MAXDATAGRAMA ;
  final Map<Primitiva,MetodoServicio> metodos;
    public java.net.DatagramSocket socket;

  private MultiMap<String, String> mapa ;

  // Inicializaciones del mapa de métodos y del socket de recepcion
  public Servidor(int puerto, int tallaDatagrama) {
      PUERTO       = puerto;
      MAXDATAGRAMA = tallaDatagrama;
      metodos = new EnumMap<>(Primitiva.class);

      mapa = new MultiMap<>();

      try {
          socket = new DatagramSocket(PUERTO);
      } catch (SocketException e) {
          System.out.println("Puerto ocupado en el servidor: " + e);
      }

  }

  public void setMetodo(Primitiva p, MetodoServicio m) {
    metodos.put(p, m);
  }

  public static void main(String [] args) {
    Servidor server = new Servidor(1099, 1024);
    server.init();
    server.start();
  }

  // aquí es donde tocamos para colgar los handlers
  public void init() {
    this.setMetodo(Primitiva.HELLO,         this::hello);
    this.setMetodo(Primitiva.PUSH,          this::push);
    this.setMetodo(Primitiva.PULL_NOWAIT,   this::pull_nowait);
    this.setMetodo(Primitiva.PULL_WAIT,     this::pull_wait);
    this.setMetodo(Primitiva.PULL_PROMISE,  this::pull_promise);
    this.setMetodo(Primitiva.NOTUNDERSTAND, this::notunderstand);
  }

  public void start() {
      new Thread(this, "Sirviente").start();
  }

  // output sólo envía mensajes con contenido, no m == null
  public void output(MensajeProtocolo m, SocketAddress sa)
     throws IOException {
      System.out.println(">>>>>> "+m); // DEBUG OUT
      if (null != m) {
          byte[] out = UDPHelper.aBytes(m);        // finalmente esta.
          socket.send(new DatagramPacket(out, out.length, sa));
      } else {
          System.err.println("output nulo para: "+sa);
      }
  }
  public void run() {
      MensajeProtocolo me;
      MensajeProtocolo ms;
      HashMap<SocketAddress, Sesion> sesiones = new HashMap<>();

      byte[] bytesIn = new byte[MAXDATAGRAMA];


      // attend protocol primitives.
      while (true) {
          try {
              DatagramPacket datagrama =
                      new DatagramPacket(bytesIn, bytesIn.length);
              socket.receive(datagrama);

              Sesion sesion;
              java.net.SocketAddress sa = datagrama.getSocketAddress();

              if ((sesion = sesiones.get(sa)) == null) {
                  sesion = new Sesion(sa);
                  sesiones.put(sa, sesion);
              }

              me = UDPHelper.aMensaje(bytesIn);
              System.out.println("<<<<<< "+me); // DEBUG OUT
              ms = metodos.get(me.getPrimitiva()).metodo_servicio(sesion, (MensajeProtocolo) me);
              if (null != ms) // el servidor puede no devolver nada ahora, como PULL_WAIT
                  output(ms, sesion.sa);
          } catch (IOException ioe) {
              ioe.printStackTrace(System.err);
          } catch (Exception e) {
              e.printStackTrace(System.err);
          }
      }
  }
    MensajeProtocolo hello(Sesion ses, MensajeProtocolo me) {
        ses.setNombre(me.getMensaje());
        try {
          return new protocol.common.MensajeProtocolo(Primitiva.HELLO, "Hola, soy UDPServer v1.0!");
        } catch (MalMensajeProtocoloException ignore) { return null; }
    }

    MensajeProtocolo push(Sesion ses, MensajeProtocolo me) {
        mapa.push(me.getIdCola(), me.getMensaje());
        synchronized (mapa) { // sí, con sus fallos :D
            mapa.notify();
        }
        try {
          return new MensajeProtocolo(Primitiva.PUSH_OK);
        } catch (MalMensajeProtocoloException ignore) { return null; }
    }

    MensajeProtocolo pull_nowait(Sesion ses, MensajeProtocolo me) {
        String mensaje;
        MensajeProtocolo ms;
        try {
          if (null != (mensaje = mapa.pop(me.getIdCola())))
                ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
          else
                ms = new MensajeProtocolo(Primitiva.NOTHING);
        } catch (MalMensajeProtocoloException ignore) { ms = null; }
        return ms;
    }

    MensajeProtocolo pull_wait(Sesion sesion, MensajeProtocolo me) {
        String mensaje;
        MensajeProtocolo ms;
        try {
            if (null != (mensaje = mapa.pop(me.getIdCola()))) {
              ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            } else {
                // prepara un hilo para atender al cliente que espera un token
                new Thread(() -> {
                    String mensaje_;
                    MensajeProtocolo ms_;
                    try {
                        synchronized (mapa) { // ya sabemos donde falla.
                            while (null == (mensaje_ = mapa.pop(me.getIdCola()))) {
                                mapa.wait();
                            }
                        }
                        try {
                            ms_ = new MensajeProtocolo(Primitiva.PULL_OK, mensaje_);
                        } catch (MalMensajeProtocoloException ignore) {
                            ms_ = null;
                        }
                        output(ms_, sesion.sa);
                    } catch (InterruptedException ie) {
                        System.out.println(ie);
                    } catch (java.io.IOException ioe) {
                        System.out.println(ioe);
                    }
                  }, "Respuesta PullWait" ).start();  // fin Thread
                // devuelve null para que output ignore el envío
                ms = null;
            }
        } catch (MalMensajeProtocoloException ignore) { ms = null; }
        return ms;
    }

    MensajeProtocolo pull_promise(Sesion sesion, MensajeProtocolo me) {
        String mensaje;
        MensajeProtocolo ms;
        try {
            if (null != (mensaje = mapa.pop(me.getIdCola()))) {
                ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            } else {
                // prepara un hilo para atender al cliente que espera un token
                Runnable respuesta = () -> {
                    String mensaje_;
                    try {
                        MensajeProtocolo ms_;
                        synchronized (mapa) {
                            while (null == (mensaje_ = mapa.pop(me.getIdCola()))) {
                                mapa.wait();
                            }
                        }
                        try {
                          ms_ = new MensajeProtocolo(Primitiva.PULL_OK, mensaje_);
                        } catch (MalMensajeProtocoloException ignore) { ms_ = null; }
                        // no vuelve con el socket de la sesión sino con la promesa
                        output(ms_, me.getSocketPromise());
                    } catch (InterruptedException ie) {
                      System.out.println(ie);
                    } catch (java.io.IOException ioe) {
                      System.out.println(ioe);
                    }
                }; // fin hilo respuesta
                new Thread(respuesta).start();
                // aquí Prometido!
                try {
                   return new MensajeProtocolo(Primitiva.PROMISED);
                } catch (MalMensajeProtocoloException ignore) { return null; }
            }
        } catch (MalMensajeProtocoloException ignore) { ms = null; }
        return ms;
    }

    MensajeProtocolo notunderstand(Sesion sesion, MensajeProtocolo me) {
      try {
          return new MensajeProtocolo(Primitiva.NOTUNDERSTAND);
      } catch (MalMensajeProtocoloException ignore) { return null; }
    }
}
