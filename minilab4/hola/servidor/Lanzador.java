package servidor;

public class Lanzador {
  public static void main(String [] args) {
    try {
      java.rmi.registry.Registry reg =
              java.rmi.registry.LocateRegistry.createRegistry(1099);
      HolaImpl o = new HolaImpl();
      reg.rebind("HolaRemoto", o);
      System.out.println("Listo el objeto 'HolaRemoto'");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
