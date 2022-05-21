FROM maven:3.8.5-jdk-11-slim as build
COPY src src
COPY pom.xml ./
RUN mvn clean package

FROM amazoncorretto:11-alpine
COPY --from=build /target/product-api-1.0-SNAPSHOT.jar ./

ENTRYPOINT ["java","-jar","product-api-1.0-SNAPSHOT.jar"]

EXPOSE 8381