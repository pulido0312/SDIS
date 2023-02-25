package fileserver.servidor;

public class FileLectorImpl extends java.rmi.server.UnicastRemoteObject
       implements fileserver.FileLector {
   private final java.io.BufferedReader br;
   public FileLectorImpl(String nombre) throws java.rmi.RemoteException,
       java.io.FileNotFoundException {
     super();
     this.br = new java.io.BufferedReader(new java.io.FileReader(nombre));
   }
   public String leeLinea() throws java.rmi.RemoteException,
       java.io.IOException {
     return br.readLine();
   }
}
