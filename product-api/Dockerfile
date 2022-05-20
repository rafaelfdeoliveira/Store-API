FROM maven:3.8.5-jdk-11-slim as Build
COPY src src
COPY pom.xml ./
RUN mvn clean package

FROM amazoncorretto:11-alpine
COPY --from=Builder /target/product-api-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["java","-jar","product-api-1.0-SNAPSHOT.jar"]

EXPOSE 8381