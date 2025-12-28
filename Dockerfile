# --- ETAPA 1: Construcción (Build) ---
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos y saltamos los tests para ir rápido en esta demo
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecución (Runtime) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos solo el JAR compilado de la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto donde corre Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]