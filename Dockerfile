
FROM openjdk:8
ADD target/devopsproject-1.0.jar operateur2image.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "operateur2image.jar"]