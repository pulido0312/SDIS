package protocol.servidor;

public class Sesion {
    public final java.net.SocketAddress sa;
    static private int idCounter = 0;
    private final int id;
    private String nombre = "";

    public Sesion(java.net.SocketAddress sa) {
        this.sa = sa;
        this.id = ++Sesion.idCounter;
    }

    public void setNombre(String nombre) {
        nombre = new String(nombre);
    }
    public String getNombre() {
        return new String(nombre);
    }
    public int getId() {
        return id;
    }

    public String toString() { // Json :P
        return "{id: "+id+", nombre: " + nombre + "}";
    }
}
