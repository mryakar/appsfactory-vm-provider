FROM openjdk:11-jre
MAINTAINER appsfactory.de
COPY target/vm-provider-1.0.0.jar vm-provider-1.0.0.jar
COPY target/classes/bootstrap.yml bootstrap.yml
ENTRYPOINT ["java","-jar","/vm-provider-1.0.0.jar"]
