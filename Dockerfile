# Use official OpenJDK as a base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file built by Maven into the container
COPY telegramBot/target/telegramBot-1.0-SNAPSHOT.jar telegramBot-1.0-SNAPSHOT.jar.original

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
