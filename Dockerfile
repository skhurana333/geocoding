FROM adoptopenjdk/maven-openjdk11

WORKDIR /geocoding

COPY  pom.xml ./
COPY src ./src

RUN mvn clean install