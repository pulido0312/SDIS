package protocol.cliente;

import protocol.common.Primitiva;

import protocol.common.MensajeProtocolo;

import protocol.common.MalMensajeProtocoloException;

public class Seek {

  final private int PUERTO = 2000;

  static java.io.ObjectInputStream ois = null;

  static java.io.ObjectOutputStream oos = null;

  public static void main(String[] args) throws java.io.IOException {

    if (args.length != 2) {

      System.out.println("Use:\njava protocol.cliente.Seek host clave");

      System.exit(-1);

    }

    String host  = args[0]; // localhost o ip|dn del servidor

    String clave = args[1]; // clave para Primitiva.SEEK

    java.net.Socket sock = new java.net.Socket(host, 2000); 

    try {

      oos = new java.io.ObjectOutputStream(sock.getOutputStream());

      ois = new java.io.ObjectInputStream(sock.getInputStream());

      pruebaPeticionRespuesta(new MensajeProtocolo(Primitiva.SEEK, clave));

    } catch (java.io.EOFException e) {

      System.err.println("Cliente: Fin de conexion.");

    } catch (java.io.IOException e) {

      System.err.println("Cliente: Error de apertura o E/S sobre objetos: "+e);

    } catch (MalMensajeProtocoloException e) {

      System.err.println("Cliente: Error mensaje Protocolo: "+e);

    } catch (Exception e) {

      System.err.println("Cliente: Excepcion. Cerrando Sockets: "+e);

    } finally {

      ois.close();

      oos.close();

      sock.close();

    }

  }

  static void pruebaPeticionRespuesta(MensajeProtocolo mp) throws java.io.IOException,

          MalMensajeProtocoloException, ClassNotFoundException {

    System.out.println("> "+mp);

    oos.writeObject(mp);

    System.out.println("< "+(MensajeProtocolo) ois.readObject());

  }

}
