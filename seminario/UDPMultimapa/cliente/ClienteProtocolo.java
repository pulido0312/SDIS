package protocol.cliente;

import protocol.common.UDPHelper;
import protocol.common.MensajeProtocolo;
import protocol.common.Primitiva;
import protocol.common.MalMensajeProtocoloException;

public class ClienteProtocolo {
  static final private int PUERTO = 1919;
  static final private int MAXDATAGRAMA = 1024;

  static java.net.InetAddress iaServer = null;
  static java.net.DatagramSocket socket = null;
  static java.net.DatagramPacket output = null, input = null;
  static byte[] bytesSalida  = null;
  static byte[] bytesEntrada = null;
    
  public static void main(String[] args) throws java.io.IOException {
    String host = "localhost";
    String linea;
        
    bytesEntrada = new byte[MAXDATAGRAMA];
        
    socket = new java.net.DatagramSocket();
    iaServer = java.net.InetAddress.getByName(host);
    socket.connect(iaServer, PUERTO);
        
    try {
      System.out.println("Pulsa <Enter> para comenzar"); System.in.read();

      prueba(new MensajeProtocolo(Primitiva.HELLO, "Pedro"));
      prueba(new MensajeProtocolo(Primitiva.PUSH, "010", "abc"));
      prueba(new MensajeProtocolo(Primitiva.PULL_NOWAIT, "112"));
      prueba(new MensajeProtocolo(Primitiva.PULL_NOWAIT, "010"));
      prueba(new MensajeProtocolo(Primitiva.PULL_WAIT, "010"));

      /**
       * aquí se espera que otro cliente inserte un mensaje
       * mensaje en la cola
       */

      /* FIN del escenario 1 */
    } catch (java.io.IOException e) {
      System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);
    } catch (Exception e) {
      System.err.println("Cliente: Excepción. Cerrando Sockets: " + e);
    } finally {
      socket.close();
    }
  }
  static void prueba(MensajeProtocolo mp) throws java.io.IOException,
          MalMensajeProtocoloException, ClassNotFoundException {

    System.out.println("> "+mp);

    // envía primitiva
    bytesSalida = UDPHelper.aBytes(mp);
    java.net.DatagramPacket output =
       new java.net.DatagramPacket(bytesSalida, bytesSalida.length,
                  iaServer, PUERTO);
    socket.send(output);

    // lee primitiva
    input  = new java.net.DatagramPacket(bytesEntrada, bytesEntrada.length);
    socket.receive(input);
    System.out.println("< "+(MensajeProtocolo)
                    UDPHelper.aMensaje(bytesEntrada));
    System.out.println("Pulsa <Enter>"); System.in.read();
  }
}
