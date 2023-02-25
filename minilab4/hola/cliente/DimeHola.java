package cliente;

public class DimeHola {
  public static void main(String [] arg) {
    try { // try descaradamente perezoso
      Object o = java.rmi.Naming.lookup("HolaRemoto");
      server.mensaje.Mensaje m = (server.mensaje.Mensaje) o;
      server.hola.Hola h = (server.hola.Hola) o;
      System.out.println(h.dime()); // pedimos un saludo
      m.setMensaje(arg[0]);         // cambiamos el mensaje en el otro servicio
      System.out.println(h.dime()); // pedimos de nuevo el saludo
    } catch (Exception e) { e.printStackTrace(); }
  }
}
