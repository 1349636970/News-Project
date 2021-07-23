FROM adoptopenjdk/openjdk15:alpine
ARG JAR_FILE=*.jar

COPY ${JAR_FILE} application.jar
#ENTRYPOINT ["java", "-jar", "application.jar","-DpropName=propValue"]
ARG datasourceURL
ARG datasourceUser
ARG datasourcePassword
ENV datasourceURL=$datasourceURL
ENV datasourceUser=$datasourceUser
ENV datasourcePassword=$datasourcePassword
ENTRYPOINT java -jar application.jar datasourceURL=$datasourceURL datasourceUser=$datasourceUser datasourcePassword=$datasourcePassword