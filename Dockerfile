# Використовуємо базовий образ Maven із JDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо всі файли в контейнер
COPY . .

# Збираємо проект
RUN mvn clean package -DskipTests

# Використовуємо мінімальний образ для запуску
FROM openjdk:17-jdk

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо зібраний JAR файл з попереднього кроку
COPY --from=build /app/target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Команда для запуску програми
CMD ["java", "-jar", "app.jar"]
