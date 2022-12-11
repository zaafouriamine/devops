FROM openjdk:8-jre-alpine
ADD target/ExamThourayaS2-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]