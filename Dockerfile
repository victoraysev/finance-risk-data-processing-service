# Use a base image with OpenJDK
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/financial-record-processing-0.0.1-SNAPSHOT.jar /app/financial-record-processing-service.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/financial-record-processing-service.jar"]
