FROM openjdk:14-alpine
ARG JAR_FILE=target/twitter-like-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} twitter-like.jar
ENTRYPOINT ["java","-jar","/twitter-like.jar"]