FROM hirokimatsumoto/alpine-openjdk-11
VOLUME /tmp

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN chmod 777 app.jar
RUN chmod 777 /tmp


MAINTAINER  Author Name mahesh_avinash1@yahoo.com
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


