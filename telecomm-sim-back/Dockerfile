FROM maven:3.9.9 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

VOLUME /tmp
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8085

CMD ["java", "-jar", "app.jar"]
