FROM adoptopenjdk/openjdk11
EXPOSE 8080
RUN mkdir /app

COPY build/libs/*.jar /app/kodo-feed-template.jar

WORKDIR /app

CMD "java" "-jar" "kodo-feed-template.jar"
