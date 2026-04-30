# Use light-weight Java image.
FROM eclipse-temurin:17-jdk-alpine

# COPY JAR
COPY target/*.jar app.jar

# RUN APP
ENTRYPOINT [ "java", "-jar", "/app.jar" ]