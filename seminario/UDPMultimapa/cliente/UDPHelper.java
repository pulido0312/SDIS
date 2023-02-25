package protocol.common;

public class UDPHelper {
  public static byte[] aBytes(MensajeProtocolo mensaje) {
    java.io.ByteArrayOutputStream bos =
         new java.io.ByteArrayOutputStream();

    try (java.io.ObjectOutput out = new java.io.ObjectOutputStream(bos)) {
      out.writeObject(mensaje);
      return bos.toByteArray();
    } catch (java.io.IOException ioe) {
      return null;
    }
  }

  public static MensajeProtocolo aMensaje(byte[] entrada) {
    java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(entrada);

    try (java.io.ObjectInput in = new java.io.ObjectInputStream(bis)) {
      return (MensajeProtocolo) in.readObject();
    } catch (java.io.IOException | ClassNotFoundException ex) {
      return null;
    }
  }
}
