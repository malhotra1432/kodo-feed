FROM adoptopenjdk/openjdk11
EXPOSE 8090
RUN useradd -d /app/ -U -m -s /bin/sh malhotra
USER malhotra
COPY build/libs/*.jar /app/catfish_service-template.jar

WORKDIR /app

ENTRYPOINT java -server -Xms1G -Xmx1G -jar catfish_service-template.jar | tee -a /var/log/containers/spring-boot-skeleton.log
