FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/vozyBackendTest-*.war
COPY ${JAR_FILE} /vozyBackendTest.war
ENTRYPOINT ["java","-jar","/vozyBackendTest.war"]
