# Estágio 1: Compilação
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Estágio 2: Execução limpa
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o jar gerado e força ele a se chamar apenas 'app.jar'
COPY --from=build /app/target/*.jar app.jar

# Execução sem usar asterisco (*), evitando que o Spring confunda argumentos
CMD ["java", "-Xmx300m", "-jar", "app.jar"]
