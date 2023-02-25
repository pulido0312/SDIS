package echoserverseguro;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

public class EchoServer {
  public static void main(String[] args) {
    try {
      SSLServerSocketFactory factoriaServer = 
              (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
      SSLServerSocket socketServidor = 
              (SSLServerSocket) factoriaServer.createServerSocket(9999);
      SSLSocket socket = (SSLSocket) socketServidor.accept(); 

      java.io.InputStream is = socket.getInputStream(); 
      java.io.InputStreamReader isr = new java.io.InputStreamReader(is);
      java.io.BufferedReader bufferedreader = new java.io.BufferedReader(isr);
      java.io.PrintWriter retorno =
	      new java.io.PrintWriter(socket.getOutputStream());

      String string = null;
      while ((string = bufferedreader.readLine()) != null) {
        System.out.println(string);
        retorno.println(string);
        retorno.flush();
        System.out.flush();
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
