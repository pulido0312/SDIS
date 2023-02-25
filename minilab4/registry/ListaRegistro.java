
package rmi.pruebas;
// pequeño  ejemplo para listar los objetos del registro
public class ListaRegistro { 
  public static void main(String [] args) {
    try {
      System.out.println("Registro: ");
      for (String n : java.rmi.Naming.list("rmi://localhost:1099/"))
        System.out.println("> " + n);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
