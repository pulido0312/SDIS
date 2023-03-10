package protocol.cliente;

import protocol.common.MensajeProtocolo;

import protocol.common.Primitiva;
import protocol.common.UDPHelper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class PullPromise {
  static final private int PUERTO = 1099;
  static final private int MAXDATAGRAMA = 1024;
  static DatagramSocket socket = null;

  public static void main(String[] args) throws java.io.IOException {

    if (args.length != 2) {
      System.out.println("Use:\njava protocol.cliente.PullPromise host clave");
      System.exit(-1);
    }
    String host  = args[0]; // loocalhost o ip/dn del servidor
    String clave = args[1]; // clave del deposito

    // para ilustrar un socket conectado
    socket = new DatagramSocket();
    socket.connect(java.net.InetAddress.getByName(host), PUERTO);
    SocketAddress saServer = socket.getRemoteSocketAddress();

    try {
      MensajeProtocolo ms = new MensajeProtocolo(Primitiva.PULL_PROMISE,
		      clave, socket.getLocalSocketAddress());
      System.out.println(">>: "+ms);
      output(ms, saServer);
      // Espera mensaje PULL_OK o muerte, :P
      MensajeProtocolo me = input();
      System.out.println("<<: "+me);
      switch (me.getPrimitiva()) {
      case PROMISED :
	      boolean[] parada = {true};
	      new Thread(() -> {
		      try {
		        MensajeProtocolo m = input();
		        System.out.println("Esto es lo que me han dado: "+m);
			parada[0] = false;
		      } catch (IOException ignore) { }
	         }, "Esperando...").start();

	      System.out.println("Me han _prometido_ un dato\n");
	      System.out.println("Voy a imprimir un rato mientras me lo pasan: ");
	      try {
                // 
	        while (true == parada[0]) {
		      Thread.sleep(300); //dormir 300 millis
		      System.out.print('.');
	        }
	      } catch (InterruptedException ie) {
		      System.out.println("Adios!");
	      }
	      break;
      case PULL_OK  :
	      System.out.println("Me han devuelto un dato");
	      break;
      default :
	      System.out.println("???");
      }
    } catch (java.io.IOException e) {
      System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);
    } catch (Exception e) {
      System.err.println("Cliente: Excepci??n. Cerrando Sockets: " + e);
    } finally {
      socket.close();
    }
  }

  // usamos socket externamente para no complicar.
  // output s??lo env??a mensajes con contenido, no m == null
  public static void output(MensajeProtocolo m, SocketAddress sa)
          throws IOException {
    if (null != m) {
      byte[] out = UDPHelper.aBytes(m);        // finalmente esta.
      socket.send(new DatagramPacket(out, out.length, sa));
    } else {
      System.err.println("output nulo para: "+sa);
    }
  }

  // usamos socket externamente para no complicar.
  public static MensajeProtocolo input() throws java.io.IOException {
    byte[] bytesIn = new byte[MAXDATAGRAMA];
    DatagramPacket datagrama = new DatagramPacket(bytesIn, bytesIn.length);
    socket.receive(datagrama);
    return UDPHelper.aMensaje(bytesIn);
  }
}
