# Solution Steps

1. Set up a new Spring Boot Java project with dependencies for Spring Web and Spring Data JPA, and configure PostgreSQL as the data source.

2. Create a User JPA entity with fields id, name, and email, and annotate with @Entity and @Table.

3. Create a UserRepository extending JpaRepository for User CRUD operations and add a method to check email uniqueness.

4. Implement the NotificationService, which contains a method simulating a long-running (email) task, e.g., with Thread.sleep.

5. Implement the UserService; for createUser, after saving the user, submit a background task to NotificationService by using an ExecutorService, ensuring email sending is done asynchronously.

6. Add a @PreDestroy method to UserService to shut down the ExecutorService cleanly on app close.

7. Implement UserController REST endpoints for CRUD and add a /health endpoint for liveness checks.

8. Configure the application.properties for PostgreSQL connection, matching the docker-compose DB service.

9. Create a Dockerfile that copies the built Spring Boot jar, sets up HEALTHCHECK with wget on the /api/users/health endpoint, and exposes port 8080.

10. Write docker-compose.yml to orchestrate postgres and userapi services, connect via environment variables, set up volume persistence for PostgreSQL, and pass the correct healthcheck.

11. Test the application locally (`docker-compose up`) to ensure non-blocking user creation (API responses quick, email simulation logs in background), health checks function, and data persists between container restarts.

