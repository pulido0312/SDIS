package protocol.servidor;

import protocol.common.MensajeProtocolo;
import protocol.common.Primitiva;
import distribuidos.mapqueue.*;

class Sirviente implements Runnable {
  private final java.net.Socket socket;
  private final Multimap<String, String> mapa;
  private final java.io.ObjectOutputStream oos;
  private final java.io.ObjectInputStream  ois;
  private final int ns;
  private static java.util.concurrent.atomic.AtomicInteger nInstance=
               new java.util.concurrent.atomic.AtomicInteger();

  Sirviente(java.net.Socket s, Multimap
           <String, String> c) throws java.io.IOException {
    this.socket = s;
    this.mapa   = c;
    this.ns     = nInstance.getAndIncrement();
    this.oos = new java.io.ObjectOutputStream(socket.getOutputStream());
    this.ois = new java.io.ObjectInputStream(socket.getInputStream());
  } // se invoca en el Servidor, usualmente

  public void run() {
    try {
      while (true) {
         String mensaje; // String multipurpose
        MensajeProtocolo me = (MensajeProtocolo) ois.readObject();
        MensajeProtocolo ms;
              // me y ms: mensajes entrante y saliente
        System.out.println("Sirviente: "+ns+": [ME: "+ me); // depuracion me
        switch (me.getPrimitiva()) {
        case HELLO:
            ms = new MensajeProtocolo(Primitiva.HELLO, "["+ns+": "+socket+"]");
            break;
        case PUSH:
            mapa.push(me.getIdCola(), me.getMensaje());
             synchronized (mapa) {
                 mapa.notify();
             } // despierta un sirviente esperando en un bloqueo de "mapa"
            ms = new MensajeProtocolo(Primitiva.PUSH_OK);
            break;
        case PULL_NOWAIT:
	    if (null != (mensaje = mapa.pop(me.getIdCola()))) {
              ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            } else {
              ms = new MensajeProtocolo(Primitiva.NOTHING);
            }
            break;
	case PULL_NOTDESTROYED:    
            if (null != (mensaje = mapa.pop_notdestroyed(me.getIdCola()))) {
              ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            } else {
              ms = new MensajeProtocolo(Primitiva.NOTHING);
            }
            break;
        case PULL_WAIT:
             synchronized (mapa) {
               while (null == (mensaje = mapa.pop(me.getIdCola()))) {
                 mapa.wait(); // duerme el sirviente actual y libera bloqueo
                }
            } 
            ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
            break;
        default:
            ms = new MensajeProtocolo(Primitiva.NOTUNDERSTAND);
        } // fin del selector segun el mensaje entrante
        oos.writeObject(ms); // concentra la escritura de mensajes ¿bueno?
         // depuracion de mensaje saliente
        System.out.println("Sirviente: "+ns+": [RESP: "+ms+"]");
      }
    } catch (java.io.IOException e) {
      System.err.println("Sirviente: "+ns+": [FIN]");
    } catch (ClassNotFoundException ex) {
      System.err.println("Sirviente: "+ns+": [ERR Class not found]");
    } catch (InterruptedException ex) {
      System.err.println("Sirviente: "+ns+": [Interrumpido wait()]");
    } catch (protocol.common.MalMensajeProtocoloException ex) {
      System.err.println("Sirviente: "+ns+": [ERR MalMensajeProtocolo !!]");
    } finally {
      // seguimos deshaciéndonos de los sockets y canales abiertos.
      try {
          ois.close();
          oos.close();
          socket.close();
      } catch (Exception x) {
          System.err.println("Sirviente: "+ns+": [ERR Cerrando sockets]");
      }
    }
  }
}
