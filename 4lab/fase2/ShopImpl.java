package servidor;
//fase2

public class  ShopImpl extends java.rmi.server.UnicastRemoteObject implements shopping.Shop {

	private String shopName;
	private shopping.Attendant dependiente;
	private boolean abierto;

	public ShopImpl(String shopName, shopping.Attendant att) throws java.rmi.RemoteException {
		super();
		this.shopName = shopName;
		this.dependiente = att;
		this.abierto = true;
	}
	public void setAttendant(shopping.Attendant att){
		this.dependiente = att;
	}

	public void use(String peticion) throws java.rmi.RemoteException {
		System.out.println("Peticion: "+peticion);
		return "Soy una tienda de nombre "+shopName;
	}
}
