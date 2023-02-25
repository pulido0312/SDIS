package protocol.servidor;

@FunctionalInterface
public interface MetodoServicio {
  protocol.common.MensajeProtocolo metodo_servicio(Sesion s, protocol.common.MensajeProtocolo m);
}
