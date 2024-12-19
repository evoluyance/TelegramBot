# Використовуйте базовий образ Maven з Java
FROM maven:3.8.5-openjdk-19 AS build

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо всі файли в контейнер
COPY . .

# Збираємо проект
RUN mvn clean package -DskipTests

# Використовуємо мінімальний образ для запуску
FROM openjdk:19-jdk

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо зібраний JAR файл з попереднього кроку
COPY --from=build /app/target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Команда для запуску програми
CMD ["java", "-jar", "app.jar"]
