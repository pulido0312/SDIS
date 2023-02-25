package fileserver.servidor;

public class FileServidorImpl extends java.rmi.server.UnicastRemoteObject
      implements fileserver.FileServidor {
   public FileServidorImpl() throws java.rmi.RemoteException {
     super();
   }
   public fileserver.FileLector abre(String nombre)
     throws java.rmi.RemoteException , java.io.FileNotFoundException {
       return new FileLectorImpl(nombre);
   }
}
