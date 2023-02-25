package echoclientseguro;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

public class EchoClient {
  public static void main(String[] arstring) {
    try {
      SSLSocketFactory factoria =
              (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket sslsocket = (SSLSocket) factoria.createSocket("localhost", 9999);
      java.io.InputStreamReader tec = new java.io.InputStreamReader(System.in);
      java.io.BufferedReader bufferedreader = new java.io.BufferedReader(tec);
      java.io.OutputStream outputstream = sslsocket.getOutputStream();
      java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(outputstream);
      java.io.BufferedWriter bufferedwriter = new java.io.BufferedWriter(osw);
      java.io.InputStreamReader isr = new java.io.InputStreamReader(sslsocket.getInputStream());
      java.io.BufferedReader bfr = new java.io.BufferedReader(isr);
      String string = null;
      while ((string = bufferedreader.readLine()) != null) {
        bufferedwriter.write(string + '\n');
        bufferedwriter.flush();
        System.out.println(bfr.readLine());
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
