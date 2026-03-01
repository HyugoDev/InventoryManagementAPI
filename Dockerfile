# --- Etapa 1: Dependencias (Cache Layer) ---
FROM maven:3.9.6-eclipse-temurin-17-alpine AS deps
WORKDIR /app
# Copiamos solo el pom para descargar dependencias.
# Si el pom no cambia, Docker saltará este paso en la próxima construcción.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# --- Etapa 2: Builder ---
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /app/pom.xml .
COPY src ./src
# Usamos parámetros para acelerar el build y limpiar lo innecesario
RUN mvn clean package -DskipTests -Dmaven.test.skip=true -T 1C

# --- Etapa 3: Runtime (Ultra-light & Secure) ---
FROM eclipse-temurin:17-jre-alpine AS final
WORKDIR /app

# 1. Seguridad: Crear un usuario no-root para ejecutar la app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# 2. Copiar solo el JAR necesario (evita el wildcard si es posible para precisión)
# Usamos un argumento para que sea flexible si cambia el nombre del jar
ARG JAR_FILE=target/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar

# 3. Ajuste de puertos (Corregido de 8080 a 8088 según tu nota)
EXPOSE 8080

# 4. Optimización de JVM para contenedores
# -XX:+ExitOnOutOfMemoryError: Si la app se queda sin memoria, el contenedor muere y orquestadores (K8s/Docker Compose) lo reinician.
ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-XX:+ExitOnOutOfMemoryError", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", "app.jar"]
