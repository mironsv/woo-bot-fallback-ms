FROM maven:3.9-amazoncorretto-21 as build
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
