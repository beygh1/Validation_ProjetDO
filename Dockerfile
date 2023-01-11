FROM maven as build
WORKDIR /app
COPY . .
RUN install

FROM openjdk:11.0
WORKDIR /app
COPY --from= build /app/target/achat.jar /app/
EXPOSE 8086
CMD ["java","-jar","/achat.jar"]