# -----------------------------
# 🌱 Build stage (multi-stage)
# -----------------------------
FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copia archivos de configuración y dependencias primero (cache eficiente)
COPY pom.xml .
COPY src ./src

# Construye el JAR con Maven (usa el profile de producción si tienes uno)
RUN mvn clean package -DskipTests

# -----------------------------
# 🚀 Run stage
# -----------------------------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia solo el JAR compilado desde el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto por defecto de Spring Boot
EXPOSE 9090

# Variables de entorno opcionales (puedes sobreescribir con --env o .env)
ENV JAVA_OPTS=""

# Ejecuta el JAR (soporta argumentos extra)
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
