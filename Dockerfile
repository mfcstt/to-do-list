FROM ubuntu:latest as build

RUN apt-get-uptade
RUN apt-get install openjdk-17 -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

EXPOSE 8080

COPY --from=build /target/to-do-list-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar" , "app.jar"]