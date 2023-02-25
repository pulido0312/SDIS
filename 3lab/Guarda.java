package cliente;
import java.rmi.Naming;
import servidor.*;
import servicio.*;

public class Guarda {
public static void main(String [ ] args) {
Valor host = (args.length < 1) ? null : args[0];
try {
servicio.Cartera or = (servicio.Cartera) Naming.lookup("rmi: "+host+"/CarteraGuarda");
or.guarda(host);
} catch (java.rmi.RemoteException re) {
System.err.println("<Cliente: Excepción RMI: "+re);
re.printStackTrace();
} catch (Exception e) {
System.err.println("<Cliente: Excepcion: "+e);
e.printStackTrace();
}
}
}
