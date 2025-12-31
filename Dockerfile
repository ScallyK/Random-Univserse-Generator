FROM eclipse-temurin:25-alpine
VOLUME /tmp
COPY target/random-universe-generator-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
