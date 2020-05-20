# Building jar outside using maven package command, becase iam facing issue with local docker copy command on my Ubuntu Eoan version when using 2 staged dockerfile.


# Create an Image
FROM openjdk:13-jdk-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/coronavirus-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

EXPOSE 8081

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
