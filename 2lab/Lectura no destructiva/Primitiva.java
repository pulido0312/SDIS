package protocol.common;

public enum Primitiva {

  HELLO,          /* nombre usuario/respuesta */

  PUSH,           /* id-cola + mensaje */

  PUSH_OK,        /* (ack de push) */

  PULL_WAIT,      /* id-cola */

  PULL_NOWAIT,    /* id-cola */

  PULL_OK,        /* mensaje (resp. pull)*/

  NOTHING,        /* (cola vacia, resp. pull_nowait) */

  NOTUNDERSTAND,  /* (error protocolo) */
	
  SEEK,			  /* PULL no destructivo */
  
  SEEK_OK;		  /* respuesta del servidor a SEEK */

}
