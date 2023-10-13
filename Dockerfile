# Primeiro estágio: compilação e construção da aplicação
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# Copia o código fonte da sua aplicação
COPY . .

# Instala o Maven e constrói o projeto
RUN apt-get install maven -y
RUN mvn clean install

# Segundo estágio: criação da imagem final
FROM openjdk:17-jdk-slim
EXPOSE 8080

# Copia o arquivo JAR da etapa anterior para a imagem final
COPY --from=build /target/to-do-list-1.0.0.jar app.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
