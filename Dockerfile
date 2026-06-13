# =====================================================================
# Estágio 1: Compilação (Build)
# =====================================================================
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copia os arquivos de configuração do Maven primeiro (melhora o cache do Docker)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Copia o código fonte da aplicação
COPY src ./src

# Garante permissão de execução para o wrapper do Maven
RUN chmod +x mvnw

# Compila o projeto e gera o arquivo .jar limpo
RUN ./mvnw clean package -DskipTests

# =====================================================================
# Estágio 2: Execução (Runtime)
# =====================================================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o arquivo .jar gerado no estágio anterior renomeando para 'app.jar'
# Isso evita o uso de asterisco (*) e impede que argumentos extras quebrem o Spring
COPY --from=build /app/target/*.jar app.jar

# Define o limite de memória e inicia a aplicação de forma segura
CMD ["java", "-Xmx300m", "-jar", "app.jar"]
