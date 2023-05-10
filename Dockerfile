FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV DATABASE_URL=db_host \
    DATABASE_PORT=db_port \
    DATABASE_USERNAME=db_name \
    DATABASE_PASSWORD=db_password
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
