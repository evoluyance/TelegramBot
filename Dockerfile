# Використовуємо офіційний образ OpenJDK
FROM openjdk:17-jdk-slim

# Додаємо зібраний JAR у контейнер
COPY target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Команда для запуску вашого JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
