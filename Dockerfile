FROM maven:3.8.6-openjdk-18
COPY . .
ENTRYPOINT ["java","-jar","target/achat-1.0.jar"]
