FROM openjdk:8
EXPOSE 8080
ADD target/kodo-feed.jar kodo-feed.jar
ENTRYPOINT ["java","-jar","/kodo-feed.jar"]
