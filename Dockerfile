FROM openjdk:21-jdk

WORKDIR /app

COPY build/libs/*.jar /app/app.jar
COPY .env .env

ENTRYPOINT ["java", "-jar", "/app/app.jar"]