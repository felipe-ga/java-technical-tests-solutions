FROM openjdk:11
ADD target/java-technical-tests-solutions.jar java-technical-tests-solutions.jar
ENTRYPOINT ["java", "-jar","java-technical-tests-solutions.jar"]
EXPOSE 8080