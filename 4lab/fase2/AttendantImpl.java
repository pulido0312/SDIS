package cliente;

public class AttendanImpl extends java.rmi.server.UnicastRemoteObject implements shopping.Attendant{
	private final String nombre;

	public AttendantImpl(String nombreTienda) throws java.rmi.RemoteException {
		super();
		this.nombre=nombreTienda;
	}

	public String update(String peticion) throws java.rmi.RemoteException {
		String respuesta = "["+nombre+"]: estamos procesando su peticion: "+peticion;
		System.out.println(respuesta);
		return respuesta;
