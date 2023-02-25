package server.mensaje;

public interface Mensaje extends java.rmi.Remote {
  public void setMensaje(String m) throws java.rmi.RemoteException;
}
