#!/usr/bin/bash -v

java -cp . -Djavax.net.ssl.trustStore=Client_Truststore.jks \
	   -Djavax.net.ssl.trustStorePassword=654321 \
           -Djavax.net.ssl.keyStore=Server_Keystore.jks \
	   -Djavax.net.ssl.keyStorePassword=123456 \
	   -Dfile.encoding=UTF-8 helloServer.Lanzador
