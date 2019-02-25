FROM java:8-jdk-alpine
COPY target/api-0.0.1-SNAPSHOT.war /home/api-0.0.1-SNAPSHOT.war
ADD target/api-0.0.1-SNAPSHOT.war /
EXPOSE 8080
CMD ["java", "-Dserver.port=8080","-jar", "/home/api-0.0.1-SNAPSHOT.war"]
