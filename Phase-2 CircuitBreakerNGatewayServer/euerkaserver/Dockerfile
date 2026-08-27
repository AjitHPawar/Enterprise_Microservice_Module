# Stage 1: Build the Spring Boot application
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy Maven configuration first for Docker layer caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy application source and build the executable JAR
COPY src ./src
RUN mvn clean package -DskipTests


# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine-3.23

WORKDIR /eurekaserver

# Copy the JAR created at /app/target in the build stage
COPY --from=build /app/target/*.jar eurekaserver.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]