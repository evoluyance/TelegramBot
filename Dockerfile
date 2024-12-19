# Використовуємо базовий образ з OpenJDK
FROM openjdk:19-jdk

# Встановлюємо Maven для збірки проекту
RUN apt-get update && apt-get install -y maven

# Вказуємо робочу директорію в контейнері
WORKDIR /app

# Копіюємо всі файли проекту до контейнера
COPY . .

# Збираємо JAR-файл
RUN mvn clean package

# Команда для запуску бота
CMD ["java", "-jar", "target/telegramBot-1.0-SNAPSHOT.jar"]
