# Використовуємо офіційний образ OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/telegramBot-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
