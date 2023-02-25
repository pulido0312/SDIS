package cliente;
public class Cliente {
  public static void main(String [] args) {
    try {
      shopping.Hall cc = (shopping.Hall)
          java.rmi.Naming.lookup("rmi://localhost:2233/RioShopping");
      shopping.Shop shop = cc.lookup(args[0]); // Tiffany's por ejemplo
      System.out.println(">>Hola, <<"+shop.use("Hola"));
    } catch (Exception e) {
      System.out.println("Error "+e);
    }  
  }
}
