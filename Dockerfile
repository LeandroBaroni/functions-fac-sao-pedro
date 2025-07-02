# Etapa 1: build do jar com Maven
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install -DskipTests

# Etapa 2: imagem final leve com apenas o jar
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/functions-fac-sao-pedro-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]