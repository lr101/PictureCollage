FROM maven:3.6.0-jdk-11-slim AS build

COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jre-slim
WORKDIR app
COPY --from=build /app/target/*.jar app.jar
COPY run.sh /app/run.sh
VOLUME /images
RUN chmod +x /app/run.sh
ENTRYPOINT ["./run.sh"]
