# Використовуємо офіційний образ Maven для збирання проекту
FROM maven:3.8.5-openjdk-17 AS build

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо файли проекту у контейнер
COPY . .

# Виконуємо збирання проекту
RUN mvn clean package -DskipTests

# Використовуємо офіційний образ OpenJDK для виконання програми
FROM openjdk:17-jdk-slim

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо jar файл із попереднього етапу
COPY --from=build /app/target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Визначаємо команду для запуску програми
CMD ["java", "-jar", "app.jar"]
