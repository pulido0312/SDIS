package shopping;

public interface Shop extends java.rmi.Remote {
  public String use(String peticion) throws java.rmi.RemoteException;
}
