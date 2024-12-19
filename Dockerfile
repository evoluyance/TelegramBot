# Використовуємо офіційний образ OpenJDK
FROM openjdk:17-jdk-slim

# Вказуємо робочу директорію всередині контейнера
WORKDIR /app

# Додаємо зібраний JAR-файл у контейнер
COPY target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Команда для запуску вашого JAR-файлу
ENTRYPOINT ["java", "-jar", "app.jar"]
