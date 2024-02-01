FROM openjdk:17-alpine
EXPOSE 8081
ADD target/CustomerMS-0.0.1-SNAPSHOT.jar CustomerMS-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/CustomerMS-0.0.1-SNAPSHOT.jar"]