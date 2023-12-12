FROM openjdk:18

COPY target/JWT-token-0.0.1-SNAPSHOT.jar /JWT-token.jar

CMD ["java", "-jar", "/JWT-token.jar"]