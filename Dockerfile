FROM openjdk:11
EXPOSE 8080
ADD target/kodo-feed-0.0.1-SNAPSHOT.jar kodo-feed-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/kodo-feed-0.0.1-SNAPSHOT.jar"]
