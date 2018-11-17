FROM java:8
VOLUME /tmp
COPY target/coding-test-*.jar /opt/spring-cloud/lib/
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/opt/spring-cloud/lib/coding-test-0.0.1-SNAPSHOT.jar"]