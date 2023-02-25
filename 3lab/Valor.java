package servicio;

public class Valor {
    private int    clave;
    private String mensajeSecreto;
    public Valor(int clave, String mensaje) {
        this.clave = clave;
        this.mensajeSecreto = new String(mensaje); }
    public boolean check(int clave)
       { return (this.clave == clave); }
    public String getMensaje()
       { return this.mensajeSecreto; }
}

