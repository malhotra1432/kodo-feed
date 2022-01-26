FROM adoptopenjdk/openjdk11
EXPOSE 8090
RUN useradd -d /app/ -U -m -s /bin/sh malhotra
USER malhotra
COPY build/libs/*.jar /app/kodo-feed.jar

WORKDIR /app

ENTRYPOINT java -server -Xms1G -Xmx1G -jar kodo-feed.jar | tee -a /var/log/containers/spring-boot-skeleton.log
