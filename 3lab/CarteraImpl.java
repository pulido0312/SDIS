package servidor;
import  servicio.*;

public class CarteraImpl extends java.rmi.server.UnicastRemoteObject implements Cartera {
	private Valor obj;
	public CarteraImpl() throws java.rmi.RemoteException {
		super();
	}
        public void guarda(Valor valor){
         this.obj = valor;
	}
        public Valor libera(int clave){
                if(obj.check(clave)){
                        return obj;
        	}else{
                	return null;
        	}
	}

}

