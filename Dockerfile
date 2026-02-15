# 1. Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# Agregamos esta línea para evitar errores de red en algunos entornos
RUN mvn dependency:go-offline -B
COPY src ./src
# Ejecutamos el package (Asegúrate de que el puerto en el código coincida con el EXPOSE)
RUN mvn clean package -DskipTests

# 2. Runtime
# Cambiamos jdk por jre (más ligero y seguro para producción)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el jar. NOTA: Asegúrate de que solo se genere UN jar en target/
# Si tu pom genera dos (el normal y el .jar.original), esto podría fallar.
COPY --from=build /app/target/*.jar app.jar

# El puerto que pusiste es el 8088.
# ¡Asegúrate de que en tu application.properties tengas: server.port=8088!
EXPOSE 8080

# Optimización para contenedores: ayuda a Java a manejar mejor la memoria RAM del VPS
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Xmx512m", "-jar", "app.jar"]
