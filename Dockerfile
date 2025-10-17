FROM openjdk:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY build/libs/user-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 CMD wget -q --spider http://localhost:8080/api/users/health || exit 1
ENTRYPOINT ["java","-jar","app.jar"]