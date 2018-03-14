FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD dockersecret-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
RUN apk add --no-cache tzdata
ENV TZ=Etc/UTC
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]