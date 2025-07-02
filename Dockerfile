# Usa a imagem base do Maven (versão 3.9.6) junto com o JDK Amazon Corretto 17.
# Esta etapa será usada apenas para build, não para rodar o container final.
FROM maven:3.9.6-amazoncorretto-17 AS build

# Define o diretório de trabalho como /app dentro do container.
WORKDIR /app

# Copia todos os arquivos do diretório atual do host para o diretório /app do container.
COPY . .

# Executa o build do projeto Maven, limpando artefatos antigos e instalando as dependências.
# O parâmetro -DskipTests faz com que os testes sejam ignorados, acelerando o build.
RUN ./mvnw clean install -DskipTests

# Usa a imagem base Amazon Corretto 17 (versão Alpine, menor e mais leve) para o runtime.
#FROM amazoncorretto:17-alpine
FROM amazoncorretto:17

# Define o diretório de trabalho como /app dentro do container final.
WORKDIR /app

# Copia o arquivo JAR gerado na etapa de build (multistage build) para o container final.
# Copia do diretório /app/target na imagem build para o diretório /app, renomeando como app.jar.
COPY --from=build /app/target/functions-fac-sao-pedro-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 para comunicação externa, normalmente usada por aplicações Spring Boot.
EXPOSE 8080

# Define o comando de inicialização do container: executa o JAR com o Java.
ENTRYPOINT ["java", "-jar", "app.jar"]