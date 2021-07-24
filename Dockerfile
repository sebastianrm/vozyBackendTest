FROM openjdk:8-jdk-alpine

#ARG server.port=8080
#ENV PORT ${server.port}
#EXPOSE ${PORT}

ARG JAR_FILE=target/portafolio-front-*.war
COPY ${JAR_FILE} /portafolio-front.war
ENTRYPOINT ["java","-jar","/portafolio-front.war"]
