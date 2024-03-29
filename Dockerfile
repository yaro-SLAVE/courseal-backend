FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /app
COPY . /app/.
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/courseal.jar
COPY --from=builder /app/static/* /app/static/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "courseal.jar"]