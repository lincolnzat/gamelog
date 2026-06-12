FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests


CMD ["sh", "-c", "java -Xmx300m -jar target/*.jar"]
