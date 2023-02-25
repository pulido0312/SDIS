package cliente;

public class ClientePropietario {
  public static void main(String [] args) {
    java.util.Scanner teclado = new java.util.Scanner(System.in);
    try {
	          javax.rmi.ssl.SslRMIClientSocketFactory rmicsf =
             new javax.rmi.ssl.SslRMIClientSocketFactory();
      javax.rmi.ssl.SslRMIServerSocketFactory rmissf =
             new javax.rmi.ssl.SslRMIServerSocketFactory();
      AttendantImpl att = new AttendantImpl(args[0],rmicsf,rmissf);  // Por ejemplo Tiffany's
        java.rmi.registry.Registry r =
         java.rmi.registry.LocateRegistry.createRegistry(2243, rmicsf, rmissf);
      shopping.Hall cc = (shopping.Hall) java.rmi.Naming.lookup("rmi://localhost:2233/RioShopping");
      if (false == cc.create(args[0], att)) {
        System.out.println("Ya existía una tienda con ese nombre. Exit!");
        System.exit(-1);
      } else {
        System.out.println("Creada la tienda: "+args[0]);
        System.out.print("0<Enter> Para terminar,\n"+
                         "1<Enter> Para abrir la tienda,\n"+
                         "2<Enter> Para cerrar la tienda.\n");
        while (true) {
          switch(teclado.nextInt()) {
            case 0: System.exit(0); break;
            case 1: cc.open(args[0], att); break;
            case 2: cc.shut(args[0], att); break;
            default:
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Error "+e);
    }  
  }
}
