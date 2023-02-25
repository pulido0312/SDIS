package udp.common;

//imports de red
import java.net.SocketAddress;

public class Sesion {
    final SocketAddress sa;
    static private int idCounter = 0;
    private final int id;
    public Sesion(SocketAddress sa) {
        this.sa = sa;
        this.id = ++Sesion.idCounter;
    }

    public int getId() {
        return id;
    }
}
