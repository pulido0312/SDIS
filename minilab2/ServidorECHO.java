package protocol.common;
import java.io.*;

public class ServidorECHO {
  public static final int PUERTO = 3000;

  public static void main(String[] args) {
      try (java.net.ServerSocket servidor = new java.net.ServerSocket(PUERTO)) {

      System.out.println("----Servidor esperando al cliente ----");
      try (java.net.Socket sock = servidor.accept()) {
  
        java.io.BufferedReader inred = new java.io.BufferedReader(new java.io.InputStreamReader(sock.getInputStream()));
        java.io.PrintStream    outred =new java.io.PrintStream(sock.getOutputStream());
  
        
        String linea = inred.readLine(); /* lee de la red */
	try{
	MensajeProtocolo mp = new MensajeProtocolo(Primitiva.HELLO,linea);
        outred.println(mp); /* echo al cliente */
	}catch (MalMensajeProtocoloException mmpe) {
	System.err.println(mmpe) ;
	}
      } catch (java.io.IOException e) {
    System.err.println("Cerrando socket de cliente");
        e.printStackTrace(System.err);
      }
    } catch (java.io.IOException e) {
      System.err.println("Cerrando socket de servicio");
      e.printStackTrace(System.err);
    }
 }
}
