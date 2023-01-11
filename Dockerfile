FROM openjdk:11.0
WORKDIR /app
ADD target/achat.jar /app/achat.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/achat.jar"]