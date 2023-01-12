FROM openjdk:11.0

WORKDIR /app

ADD target/achat-1.0.jar /app/achat-1.0.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","/app/achat-1.0.jar"]