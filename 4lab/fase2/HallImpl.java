package servidor;
//fase2
public class HallImpl extends java.rmi.server.UnicastRemoteObject
  implements shopping.Hall {
  java.util.Map<String, servidor.ShopImpl>mapaTiendas = new java.util.HashMap<>();
  private String nombre;
  public HallImpl(String nombre) throws java.rmi.RemoteException {
    super();
    this.nombre = nombre;
  }
 
  public shopping.Shop lookup(String nombreTienda) throws java.rmi.RemoteException {
    	return tiendas.get(nombreTienda);
  }
  public boolean create(String nombreTienda, shopping.Attendant att) throws java.rmi.RemoteException {
	 ShopImpl tienda = tiendas.get(nombreTienda);
	if (tienda == null){
	       tienda = new ShopImpl(nombreTienda, att);
       		System.out.println("Creada la tienda : " +nombreTienda);
		tiendas.put(nombreTienda, tienda);
		return true;	
	} else {
	       return false;
	}	       
}
 public void shut(String nombreTienda, shopping.Attendant att) throws java.rmiRemoteException{
 
 }
public void open(String nombreTienda, shopping.Attendant att) throws java.rmiRemoteException{
 
 }
}
