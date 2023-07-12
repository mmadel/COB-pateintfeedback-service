FROM openjdk:8-jre-alpine
VOLUME /tmp 
ADD target/cob-patient-feedback-service-1.0.1.jar .
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
