FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/secure-teacher-portal-1.0.0.jar app.jar
EXPOSE 8080
USER 1001
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
