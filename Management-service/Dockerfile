# Use Java 17 as the base image for building
FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY pom.xml mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -Dmaven.test.skip

# Use Java 17 as the base image for the final runtime
FROM openjdk:17-jdk-slim

WORKDIR /managementservice

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar /managementservice/management-0.0.1-SNAPSHOT.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "management-0.0.1-SNAPSHOT.jar"]
