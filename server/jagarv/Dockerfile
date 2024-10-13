
FROM openjdk:21-jdk-slim AS build


RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app
COPY pom.xml ./ 
COPY src ./src


RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim

WORKDIR /app


COPY --from=build /app/target/jagarv-0.0.1-SNAPSHOT.jar /app/jagarv.jar


EXPOSE 8080


CMD ["java", "-jar", "/app/jagarv.jar"]
