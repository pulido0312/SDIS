#!/usr/bin/bash -v

java -cp . -Djavax.net.ssl.trustStore=Client_Truststore.jks \
	   -Djavax.net.ssl.trustStorePassword=654321 \
	   -Dfile.encoding=UTF-8 echoclientseguro.EchoClient
