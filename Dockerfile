FROM java:8-jdk-alpine
COPY target/api-0.0.1-SNAPSHOT.jar /home/api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-Dserver.port=8080","-jar", "/home/api-0.0.1-SNAPSHOT.jar"]
