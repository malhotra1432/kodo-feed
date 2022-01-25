FROM adoptopenjdk/openjdk11
EXPOSE 8080

COPY build/libs/*.jar /app/kodo-feed-template.jar

WORKDIR /app

ENTRYPOINT java -server -Xms1G -Xmx1G -jar kodo-feed-template.jar | tee -a /var/log/containers/spring-boot-skeleton.log
