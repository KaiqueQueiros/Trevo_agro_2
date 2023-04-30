FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080:8080
CMD ["--network", "REDE1"]
ENV DATABASE_HOST=db_host \
    DATABASE_PORT=db_port \
    DATABASE_NAME=db_name \
    DATABASE_USER=db_user \
    DATABASE_PASSWORD=db_password