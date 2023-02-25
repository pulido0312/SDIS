#!/usr/bin/bash -v

java -cp . -Djavax.net.ssl.keyStore=Server_Keystore.jks \
	   -Djavax.net.ssl.keyStorePassword=123456 \
	   -Dfile.encoding=UTF-8 echoserverseguro.EchoServer
