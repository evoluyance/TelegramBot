# Етап 1: Збірка проекту
FROM maven:3.8.8-openjdk-17 AS builder

# Робоча директорія
WORKDIR /app

# Копіюємо всі файли проекту в контейнер
COPY . .

# Виконуємо команду збірки Maven
RUN mvn clean package -DskipTests

# Етап 2: Запуск програми
FROM openjdk:17-jdk-slim

# Робоча директорія
WORKDIR /app

# Копіюємо зібраний JAR-файл з попереднього етапу
COPY --from=builder /app/target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Команда для запуску JAR-файлу
ENTRYPOINT ["java", "-jar", "app.jar"]
