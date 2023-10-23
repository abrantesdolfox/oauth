FROM ubuntu:latest AS BUILD

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
#FROM openjdk:17-alpine

COPY . .

#RUN apt-get install maven -y
RUN apt-get -y install maven
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY --from=build /target/oauth-2-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]