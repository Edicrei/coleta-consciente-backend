FROM adoptopenjdk/openjdk11-openj9 AS base
COPY target/faleconosco-*.jar /app.jar

EXPOSE 8082

CMD java -jar /app.jar