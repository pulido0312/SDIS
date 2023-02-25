package servidor;
import java.rmi.RemoteException;
public class Servidor {
  public static void main(String [] args) {
    try {
      java.rmi.registry.Registry reg =
          java.rmi.registry.LocateRegistry.createRegistry(2233);
      HallImpl hall = new HallImpl("RioShopping");
      reg.rebind("RioShopping", hall);
      System.out.println("Registrado objeto remoto RioShopping");
    } catch (RemoteException e) {
      System.out.println("Error "+e);
    }  
  }
}

