# Ejemplo Echo de comunicación UDP en Java con Session

Los archivos cliente y servidor contienen los programas fuente java
respectivos. Para compilar y ejecutar:
1. Abra un terminal en el interior de cada folder.
2. compile y ponga en marcha el servidor:
   1. Entre en el folder `servidor`: `cd servidor`
   2. Compile el fuente: `javac -d . Sesion.java`
   2. Compile el fuente: `javac -d . UDPEchoSessionServer.java`
   3. Ponga en marcha el programa: `java udp.server.UDPEchoSessionServer`
   4. Posiblemente Windows le pedirá que acepte la construcción de un servicio.
3. compile y ponga en marcha el cliente:
   1. Entre en el folder `cliente`: `cd cliente`
   2. Compile el fuente: `javac -d . UDPClienteSimple.java`
   3. Ponga en marcha el programa: `java udp.echo.ClienteSimple`
   4. Escriba líneas y compruebe cómo se reproducen de vuelta. El servidor
      también reproduce las líneas.
4. Puede lanzar (puntos 3 y 4 anteriores) otro cliente simultáneamente y ver
   cómo se admite una nueva sesión.
5. Puede cerrar un cliente y lanzarlo de nuevo para ver si se crea una nueva
   sesión.
