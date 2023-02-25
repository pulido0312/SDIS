package servidor;

public class HolaImpl extends java.rmi.server.UnicastRemoteObject
  implements server.hola.Hola, server.mensaje.Mensaje {
  private String mensaje = "---"; // este es el mensaje que podremos cambiar

  public HolaImpl() throws java.rmi.RemoteException {
    super();
    setLog(System.err); // comentar o descomentar para log
  }
  // para la interfaz 'Hola'
  public String dime() throws java.rmi.RemoteException {
    try { // huella por pantalla via println
      System.out.println("**** dime desde: "+this.getClientHost());
    } catch (java.rmi.server.ServerNotActiveException snae) {}
    return mensaje;
  }
  // para la interfaz 'Mensaje'
  public void setMensaje(String mensaje) throws java.rmi.RemoteException {
    try { // huella por pantalla via println
      System.out.println("****setMensaje desde: "+this.getClientHost());
    } catch (java.rmi.server.ServerNotActiveException snae) {}
    this.mensaje = mensaje;
  }
}
