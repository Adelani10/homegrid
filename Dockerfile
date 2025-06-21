FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/homegrid-0.0.1-SNAPSHOT.jar homegrid.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "homegrid.jar"]
