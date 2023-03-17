FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} springreact-docker-1.0.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/springreact-docker-1.0.jar"]