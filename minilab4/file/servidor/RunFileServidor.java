package fileserver.servidor;

public class RunFileServidor {
   public static void main(String[] args) {
     try {
       fileserver.FileServidor cc = new FileServidorImpl();

       java.rmi.registry.Registry registro =
          java.rmi.registry.LocateRegistry.createRegistry(1099);
       registro.rebind("ObjetoFileServidor", cc);
       System.out.println("Objeto remoto 'ObjetoFileServidor' enlazado");
     } catch (java.rmi.RemoteException re) {
       re.printStackTrace(System.err);
     } catch (Exception e) {
       e.printStackTrace(System.err);
     }
   }
}
