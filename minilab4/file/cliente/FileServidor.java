package fileserver;

public interface FileServidor extends java.rmi.Remote {
    public FileLector abre(String nombre)
        throws java.rmi.RemoteException, java.io.FileNotFoundException;
}
