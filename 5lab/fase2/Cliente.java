package cliente;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
  public static void main(String [] args) {
    try {
      javax.rmi.ssl.SslRMIClientSocketFactory rmicsf =
            new javax.rmi.ssl.SslRMIClientSocketFactory();
      java.rmi.registry.Registry reg =
            java.rmi.registry.LocateRegistry.getRegistry("localhost", 2233, rmicsf);
	
      shopping.Hall cc = (shopping.Hall) java.rmi.Naming.lookup("rmi://localhost:2233/RioShopping");
      shopping.Shop shop = cc.lookup(args[0]);
      if (null == shop) {
          System.out.println("La tienda no existe");
      } else {
          System.out.println("<<"+shop.use("Hola"));
      }
    } catch (MalformedURLException | NotBoundException | RemoteException e) {
      System.out.println("Error "+e);
    }  
  }
}
