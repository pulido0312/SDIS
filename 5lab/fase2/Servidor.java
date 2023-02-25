package servidor;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Servidor {
  public static void main(String [] args) {
    try {
	javax.rmi.ssl.SslRMIClientSocketFactory rmicsf =
             new javax.rmi.ssl.SslRMIClientSocketFactory();
      javax.rmi.ssl.SslRMIServerSocketFactory rmissf =
             new javax.rmi.ssl.SslRMIServerSocketFactory();

      java.rmi.registry.Registry reg = java.rmi.registry.LocateRegistry.createRegistry(2233,rmicsf,rmissf);
      HallImpl hall = new HallImpl("RioShopping",rmicsf,rmissf);
      reg.rebind("RioShopping", hall);
      System.out.println("Registrado objeto remoto RioShopping");
    } catch (RemoteException e) {
      System.out.println("Error "+e);
    }  
  }
}
