FROM adoptopenjdk/maven-openjdk11

WORKDIR /geocoding

COPY  pom.xml ./
COPY src ./src

RUN echo $(ls -ltr)
RUN mvn clean install
RUN echo $(ls -ltr target/)
#CMD ["java", "-jar", "target/geocoding-1.0-SNAPSHOT.jar" ]
