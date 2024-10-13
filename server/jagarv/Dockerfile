
FROM maven:3.8.7-eclipse-temurin-21 AS build
WORKDIR /app


COPY . .


RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /app


COPY --from=build /app/target/jagarv-0.0.1-SNAPSHOT.jar /app/jagarv.jar

EXPOSE 8080


CMD ["java", "-jar", "/app/jagarv.jar"]
