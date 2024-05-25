# Start with a base image containing Java runtime
FROM openjdk:17-oracle

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=./target/game-app-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]