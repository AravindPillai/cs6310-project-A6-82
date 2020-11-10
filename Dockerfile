FROM openjdk:15-alpine
RUN addgroup -S A82 && adduser -S spring -G A82
USER spring:A82
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]