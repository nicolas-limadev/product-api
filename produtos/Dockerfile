FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} springreact-docker-1.0.jar
ENTRYPOINT ["java","-jar","/springreact-docker-1.0.jar"]