FROM java:8
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]