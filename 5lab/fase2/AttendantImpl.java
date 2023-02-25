package cliente;


public class AttendantImpl extends java.rmi.server.UnicastRemoteObject implements shopping.Attendant {
    private final String nombre;
    
    public AttendantImpl(String nombreTienda,java.rmi.server.RMIClientSocketFactory rmicsf,  // factory de cliente
        java.rmi.server.RMIServerSocketFactory rmissf) throws java.rmi.RemoteException {
        super(0,rmicsf,rmissf);
        this.nombre = nombreTienda;
    }
    public String update(String peticion) throws java.rmi.RemoteException {
        String respuesta = "["+nombre+"]: estamos procesando su peticion: "+peticion;
        System.out.println(respuesta);
        return respuesta;
    }
}
