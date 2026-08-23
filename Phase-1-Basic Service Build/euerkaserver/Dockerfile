#Add Dockerfile in two stages
#1.Maven Build Stage
FROM maven:3.9.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

#2.Application Runtime Stage
FROM openjdk:11-jre-slim
WORKDIR /eurekaserver
COPY --from=build /app/target/*.jar /eurekaserver/eurekaserver.jar
ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]
