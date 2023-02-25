package protocol.common;

import java.net.SocketAddress;

public class MensajeProtocolo implements java.io.Serializable {
  private final Primitiva primitiva;
  private final SocketAddress socketPromise; // para PULL_PROMISE
  private final String mensaje; /* HELLO, PUSH, PULL_OK */
  private final String idCola;  /* PUSH, PULL_WAIT, PULL_NOWAIT */

  /* Constructor para PUSH_OK, NOTHING, NOTUNDERSTAND */
  public MensajeProtocolo(Primitiva p)
      throws MalMensajeProtocoloException {
    if (p == Primitiva.PUSH_OK || p == Primitiva.NOTUNDERSTAND ||
        p == Primitiva.NOTHING || p == Primitiva.PROMISED) {
       this.primitiva = p;
       this.mensaje = this.idCola = null;
       this.socketPromise = null;
    } else
       throw new MalMensajeProtocoloException();
  }

  /* Constructor para HELLO, PULL_OK, PULL_WAIT, PULL_NOWAIT */
  public MensajeProtocolo(Primitiva p, String mensaje)
      throws MalMensajeProtocoloException {
    if (p == Primitiva.HELLO || p == Primitiva.PULL_OK) {
      this.mensaje = mensaje;
      this.idCola  = null;
      this.socketPromise = null;
    } else if (p == Primitiva.PULL_WAIT || p == Primitiva.PULL_NOWAIT) {
      this.idCola  = mensaje;
      this.mensaje = null;
      this.socketPromise = null;
    } else
      throw new MalMensajeProtocoloException();
    this.primitiva = p;
  }

  /* Constructor para PUSH */
  public MensajeProtocolo(Primitiva p, String idCola, String mensaje)
          throws MalMensajeProtocoloException {
    if (p == Primitiva.PUSH) {
      this.primitiva = p;
      this.mensaje = mensaje;
      this.idCola = idCola;
      this.socketPromise = null;
    } else
      throw new MalMensajeProtocoloException();
  }

  /* Constructor para PULL_PROMISE */
  public MensajeProtocolo(Primitiva p, String idCola, SocketAddress sp)
      throws MalMensajeProtocoloException {
    if (p == Primitiva.PULL_PROMISE) {
      this.primitiva = p;
      this.mensaje = null;
      this.idCola = idCola;
      this.socketPromise = sp;
    } else 
      throw new MalMensajeProtocoloException();
  }

  public Primitiva     getPrimitiva()     { return this.primitiva; }
  public String        getName()          { return this.primitiva.name(); }
  public String        getMensaje()       { return this.mensaje; }
  public String        getIdCola()        { return this.idCola; }
  public SocketAddress getSocketPromise() { return this.socketPromise; }

  public String toString() { /* prettyPrinter de la clase */
    switch (this.primitiva) {
      case HELLO: 
      case PULL_OK:
        return this.primitiva+":"+this.mensaje ;
      case PULL_WAIT:
      case PULL_NOWAIT:
        return this.primitiva+":"+this.idCola ;
      case PUSH:
        return this.primitiva+":"+this.idCola+":"+this.mensaje ;
      case PULL_PROMISE:
        return this.primitiva+":"+this.idCola+":"+this.socketPromise;
      default :
        return this.primitiva.toString() ;
    }
  }
}
