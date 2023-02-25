package shopping;

public interface Hall extends java.rmi.Remote {
    public boolean create(String nombreTienda, Attendant att) throws java.rmi.RemoteException;
    public void    shut(String nombreTienda, Attendant att) throws java.rmi.RemoteException;
    public void    open(String nombreTienda, Attendant att) throws java.rmi.RemoteException;
    public Shop    lookup (String nombreTienda) throws java.rmi.RemoteException;
}
