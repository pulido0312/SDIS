package servidor;

public class  ShopImpl extends java.rmi.server.UnicastRemoteObject implements shopping.Shop {

	private String shopName;

	public ShopImpl(String shopName) throws java.rmi.RemoteException {
		super();
		this.shopName = shopName;
	}

	public String use(String peticion) throws java.rmi.RemoteException {
		System.out.println("Peticion: "+peticion);
		return "Soy una tienda de nombre "+shopName;
	}
}
