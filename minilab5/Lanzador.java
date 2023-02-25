package helloServer;

public class Lanzador {
  public static void main(String[] args) {
    try {
      javax.rmi.ssl.SslRMIClientSocketFactory rmicsf =
             new javax.rmi.ssl.SslRMIClientSocketFactory();
      javax.rmi.ssl.SslRMIServerSocketFactory rmissf =
             new javax.rmi.ssl.SslRMIServerSocketFactory();

      // creamos un registro que permita sockets seguros
      java.rmi.registry.Registry r =
         java.rmi.registry.LocateRegistry.createRegistry(1100, rmicsf, rmissf);

      helloServer.HolaImpl holaObj =
         new helloServer.HolaImpl("Saludos!", rmicsf, rmissf);      
      r.rebind("ObjetoHola", holaObj);
    } catch (Exception e) {
      System.err.println("Excepción: " + e);
    }
  }
}
