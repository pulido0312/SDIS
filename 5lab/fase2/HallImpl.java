package servidor;

import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

public class HallImpl extends java.rmi.server.UnicastRemoteObject implements shopping.Hall {
  private String nombre;
  private java.util.Map<String, ShopImpl> tiendas    
          = new java.util.HashMap<>();
  RMIClientSocketFactory rmicsf;
  RMIServerSocketFactory rmissf;
    
  public HallImpl(String nombre,java.rmi.server.RMIClientSocketFactory rmicsf,  // factory de cliente
        java.rmi.server.RMIServerSocketFactory rmissf)  // factory de server) 
	throws java.rmi.RemoteException {
	super(0,rmicsf,rmissf);
	this.rmicsf = rmicsf;
	this.rmissf = rmissf;
      this.nombre = nombre;
  }
  public shopping.Shop lookup(String nombreTienda) throws java.rmi.RemoteException {
      return tiendas.get(nombreTienda);
  }
  public boolean create(String nombreTienda, shopping.Attendant att)
          throws java.rmi.RemoteException {
    ShopImpl tienda = tiendas.get(nombreTienda);
    if (null == tienda) { // no existe la tienda, crearla
        tienda = new ShopImpl(nombreTienda, att,rmicsf,rmissf);
        System.out.println("Creada la tienda: "+nombreTienda);
        tiendas.put(nombreTienda, tienda);
        return true;
    } else {
        return false;  
    }
  }
  public void shut(String nombreTienda, shopping.Attendant att)
          throws java.rmi.RemoteException {
    System.out.println("Cerrando: "+nombreTienda);
    ShopImpl tienda = tiendas.get(nombreTienda);
    tienda.cierra(att);
  }
  public void open(String nombreTienda, shopping.Attendant att)
          throws java.rmi.RemoteException {
    System.out.println("Abriendo: "+nombreTienda);
    ShopImpl tienda = tiendas.get(nombreTienda);
    tienda.abre(att);
  } 
}
