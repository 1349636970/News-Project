FROM openjdk:15-alpine
ARG JAR_FILE=*.jar

COPY ${JAR_FILE} application.jar
ARG datasourceURL
ARG datasourceUser
ARG datasourcePassword
ENV datasourceURL=${datasourceURL} \
    datasourceUser=${datasourceUser} \
    datasourcePassword=${datasourcePassword}
ENTRYPOINT java -jar application.jar --datasourceURL=${datasourceURL} --datasourceUser=${datasourceUser} --datasourcePassword=${datasourcePassword}