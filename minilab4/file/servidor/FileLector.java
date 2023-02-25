package fileserver;

public interface FileLector extends java.rmi.Remote {
    String leeLinea() throws java.rmi.RemoteException, java.io.IOException;
}
