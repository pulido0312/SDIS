package servidor;

public class HallImpl extends java.rmi.server.UnicastRemoteObject
  implements shopping.Hall {
  java.util.Map<String, servidor.ShopImpl>mapaTiendas = new java.util.HashMap<>();
  private String nombre;
  public HallImpl(String nombre) throws java.rmi.RemoteException {
    super();
    this.nombre = nombre;
  }
 
  public shopping.Shop lookup(String nombreTienda) throws java.rmi.RemoteException {
    	ShopImpl nuevaTienda = new ShopImpl(nombreTienda);
    	mapaTiendas.put(nombreTienda, nuevaTienda);
    	return nuevaTienda;
  }
}

