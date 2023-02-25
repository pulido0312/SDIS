# Ejercicio de servidor de colas de mensajes con UDP - Versión Chachi

Este ejercicio reproduce aproximadamente el comportamiento del servicio
ya programado con TCP para colas de mensajes con clave. Diferencias
principales con la versión 1:
- Incluye sesiones (`Sesion`).
- Adopta un nuevo modelo de inyección de código de servicio.
- Agrega la primitiva `PULL_PROMISE` (y `PROMISED`).
- Incluye clientes unitarios: `Hello`, `Push`, `PullWait`, `PullNoWait` y
  `PullPromise`. 

Está separado en folders cliente y servidor con los programas fuente
de cada lado por separado.  Para compilar y ejecutar:
1. Abra un terminal en el interior de cada folder.
2. compile y ponga en marcha el servidor:
   1. Entre en el folder `servidor`: `cd servidor`
   2. Compile las fuentes: `javac -d . *.java`
   3. Ponga en marcha el programa: `java protocol.servidor.Servidor`
   4. Posiblemente Windows le pedirá que acepte la construcción de un servicio.
3. compile y ponga en marcha el cliente:
   1. Entre en el folder `cliente`: `cd cliente`
   2. Compile las fuentes: `javac -d . *.java`
   3. Ponga en marcha el programa: `java protocol.cliente.ClienteProtocolo`
   4. El cliente es una reproducción del escenario de interacción propuesto
      como ejemplo en el Laboratorio 2. Según pulsa `<Enter>` se va avanzando
      en el escenario.
4. Probar con los diferentes `Pull*` y agregar con `Push` antes de invocar
   y después de invocar.  Hay 6 posibilidades.
