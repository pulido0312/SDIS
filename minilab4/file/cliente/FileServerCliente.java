package fileservercliente;

public class FileServerCliente {
    public static void main(String[] args) {
        String nombreArchivo = "Hola.txt";
        fileserver.FileLector fl;
        fileserver.FileServidor fs;
        String linea;

        try {
            fs = (fileserver.FileServidor) java.rmi.Naming.lookup("rmi://localhost:1099/ObjetoFileServidor");

            try {
                fl = fs.abre(nombreArchivo);
                try {
                    while (null != (linea = fl.leeLinea())) {
                        System.out.println(nombreArchivo+ ": "+linea);
                    }
                } catch (java.io.EOFException ioe) {
                    System.err.println("Archivo finalizado con EOFException");
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
            } catch (java.io.FileNotFoundException fnfe) {
                System.err.println("El archivo " + nombreArchivo + " no existe.");
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
