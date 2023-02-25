package shopping;

public interface Attendant extends java.rmi.Remote {
  public String update(String peticion) throws java.rmi.RemoteException;
}
