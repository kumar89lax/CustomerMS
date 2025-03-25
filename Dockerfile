FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8081
COPY target/CustomerMS-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
