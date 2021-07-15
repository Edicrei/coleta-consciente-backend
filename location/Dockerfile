FROM adoptopenjdk/openjdk11-openj9 AS base
COPY target/location-*.jar /app.jar

EXPOSE 8083

CMD java -jar /app.jar