# Step 1: Use Maven with JDK 17 to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only your application directory
COPY proddaturiMinApp/ ./proddaturiMinApp/

# Move into app directory for build
WORKDIR /app/proddaturiMinApp

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Step 2: Use a lightweight JDK image to run the built JAR
FROM eclipse-temurin:17-jdk

# Create a working directory
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/proddaturiMinApp/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
