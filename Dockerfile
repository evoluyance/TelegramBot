# Use Maven image for build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy project files to container
COPY . .

# Build the application and create the JAR
RUN mvn clean package -DskipTests

# Use lightweight JDK image for running the application
FROM openjdk:17-jdk-slim

# Set working directory for the application
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/telegramBot-1.0-SNAPSHOT.jar app.jar

# Expose port (optional, depends on your bot architecture)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
