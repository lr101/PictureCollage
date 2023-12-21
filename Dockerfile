FROM maven:3.6.0-jdk-11-slim AS build

COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jre-slim
WORKDIR app
COPY --from=build target/*.jar app.jar
VOLUME /images
RUN export IMAGES_PATH="/images"
ENTRYPOINT ["java", "-jar", "app.jar"]
