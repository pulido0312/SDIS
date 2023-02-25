package shopping;
public interface Hall extends java.rmi.Remote {
  public Shop lookup (String nombreTienda)
         throws java.rmi.RemoteException;
}
