# ----- Build Stage -----
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom first (for caching dependencies)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy source code
COPY src ./src


# Build JAR
RUN mvn clean package -DskipTests



# ----- Run Stage -----.
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run app
ENTRYPOINT [ "java", "-jar", "/app.jar" ]