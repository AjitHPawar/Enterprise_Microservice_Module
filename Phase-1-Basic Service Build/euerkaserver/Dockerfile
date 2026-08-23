#Add Dockerfile in two stages
#1.Maven Build Stage
FROM maven:3-amazoncorretto-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

#2.Application Runtime Stage
FROM openjdk:21-alpine
WORKDIR /eurekaserver
COPY --from=build /app/target/*.jar /eurekaserver/eurekaserver.jar
ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]
