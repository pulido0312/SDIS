/*
 * ClienteSimple: Cliente UDP de Strings de teclado en bucle.
 */
package udp.echo;

import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ClienteSimple {
  public static void main(String[] args) throws java.io.IOException {
    // Prescindimos de los argumentos para hacerlo más sencillo.
    // Si estás en el laboratorio en este momento coméntale a tu profesor
    // que arranque un servidor en la máquina del profesor y
    // conéctate usando un ordenador del aula.
    String host = "localhost";
    String linea;
    int port = 1919; // este es el puerto del servidor.
    InetAddress ia = InetAddress.getByName(host);
    DatagramSocket socket = new DatagramSocket();
    socket.connect(ia, port); // para mostrar cómo se conecta.
        
    Reader r1 = new InputStreamReader(System.in);
    BufferedReader teclado = new BufferedReader(r1); // teclado 

    while ((linea = teclado.readLine()) != null) {
      byte[] dataOut = linea.getBytes();
      DatagramPacket output =
                new DatagramPacket(dataOut, dataOut.length, ia, port);
      socket.send(output);
      byte[] dataIn = new byte[160];        
      DatagramPacket input =
            new DatagramPacket(dataIn, dataIn.length);
        
      socket.receive(input);
      linea = new String(input.getData());
      System.out.println("Echo: "+linea);
      if (linea.startsWith("Adios.")) System.exit(0);
    }

    // Si quieres saber que pasa al usar un puerto distinto al que tienes
    // conectado, cambia el bucle anterior por esto:
    // byte[] dataOut = linea.getBytes(new String("Mensaje tonto"));
    // DatagramPacket output =
    //    new DatagramPacket(dataOut, dataOut.length, ia, 2000);
    //  socket.send(output);
  }
}
