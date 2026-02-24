FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} springAI.jar

ENTRYPOINT ["java", "-jar", "/springai.jar"]

EXPOSE 8761

