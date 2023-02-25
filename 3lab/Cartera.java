package servicio;
import java.rmi.Remote;
import java.rmi.RemoteException;
import servicio.Valor;


public interface Cartera extends Remote{
	
    	public void guarda(Valor valor);
    	public Valor libera(int clave);
}
