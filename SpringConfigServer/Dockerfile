FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY jce_policy-8/* /usr/lib/jvm/java-16-openjdk/jre/lib/security
COPY target/SpringConfigServer-0.0.1-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java","-Djava.security.egd=File:/dev/./urandom","-jar","ConfigServer.jar"]