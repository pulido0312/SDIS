package udp.server;

// imports propios
import udp.common.Sesion;
// imports de red
import java.net.SocketAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;
// imports util
import java.io.IOException;
import java.util.HashMap;

public class UDPEchoSessionServer {
  static final int BUFFERSIZE = 256;
  static final HashMap<SocketAddress,Sesion> sesiones = new HashMap<>();

  public static void main(String[] args) {
    DatagramSocket sock;
    try {
      sock = new DatagramSocket(1919);
    } catch (SocketException e) {
      System.out.println(e);
      return;
    }
    // echo de todo sin importar el origen.
    while (true) {
      try {
        DatagramPacket pack =
           new DatagramPacket(new byte[BUFFERSIZE], BUFFERSIZE);
    
        sock.receive(pack);
        Sesion s;
        // Si no se encuentra el SocketAddres entre las claves
	//   se crea una nueva entrada.
        if ((s = sesiones.get(pack.getSocketAddress())) == null) {
            s = new Sesion(pack.getSocketAddress());
            sesiones.put(pack.getSocketAddress(), s);
        }
        String linea = "<"+s.getId()+">["+pack.getAddress().getHostAddress()+
                 ":"+pack.getPort()+"] "+(new String(pack.getData()));
        System.out.println(linea);

        byte[] buffer = linea.getBytes();

        pack.setData(buffer); // no es preciso setLength para enviar.

        sock.send(pack);      // se aprovecha el mismo datagrama.
      } catch (IOException ioe) {
        System.out.println(ioe);
      }
    }
  }
}
