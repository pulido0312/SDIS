package servidor;

public class ShopImpl extends java.rmi.server.UnicastRemoteObject implements shopping.Shop {
    private String nombre;
    private shopping.Attendant dependiente;
    private boolean abierto;
    
    public ShopImpl(String nombre, shopping.Attendant att,java.rmi.server.RMIClientSocketFactory rmicsf,
        java.rmi.server.RMIServerSocketFactory rmissf)
            throws java.rmi.RemoteException {
      super(0,rmicsf,rmissf);
      this.nombre      = nombre;
      this.dependiente = att;
      this.abierto     = true;
    }
    public void setAttendant(shopping.Attendant att) {
      this.dependiente = att;
    }
    public String use(String peticion) throws java.rmi.RemoteException {
      if (abierto) {
        String respuesta = dependiente.update(peticion);
        System.out.println("["+nombre+"] Peticion:  "+peticion);
        System.out.println("["+nombre+"] Respuesta: "+respuesta);
        return respuesta;
      } else {
        return "La tienda esta cerrada por hoy";
      }
    }
    public void cierra(shopping.Attendant att) {
      abierto = (att.equals(dependiente)) ? false: abierto ;
    }
    public void abre(shopping.Attendant att) {
      abierto = (att.equals(dependiente)) ? true : abierto ;
    }
}
