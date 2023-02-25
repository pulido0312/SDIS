package servidor;
public class Lanzador {
public static void main(String [ ] args) {
try {
CarteraImpl oRemoto = new CarteraImpl();
 
java.rmi.registry.Registry registro =
java.rmi.registry.LocateRegistry.getRegistry("localhost");
 
registro.rebind("CarteraRemota", oRemoto);
System.err.println("Servidor preparado");
} catch (Exception e) {
System.err.println("Excepción del servidor: "+e.toString());
e.printStackTrace();
}
}
}
