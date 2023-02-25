//package protocol.common;

public class ClienteECHO {
  public static final int PUERTO = 3000;

  public static void main(String[] args) {
    try {
      java.io.BufferedReader tec =
     new java.io.BufferedReader(
        new java.io.InputStreamReader(System.in));

      java.net.Socket miSocket = new java.net.Socket("localhost", PUERTO);

      java.io.BufferedReader inred =
     new java.io.BufferedReader(
            new java.io.InputStreamReader(miSocket.getInputStream()));

      java.io.PrintStream outred = new java.io.PrintStream(miSocket.getOutputStream());

      //String linea = tec.readLine();  // lee de teclado
      String linea =("hola");
      outred.println(linea);                   // envia al servidor
      linea = inred.readLine();                // lee del servidor
      System.out.println("Recibido: "+linea);  // eco local del servidor
      
    } catch (Exception e) { e.printStackTrace(); }
  }
}
