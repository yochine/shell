FROM zrxjava/java:8
MAINTAINER zrxjava
ENV TZ=Asia/Shanghai
EXPOSE 9091
WORKDIR /shell
ARG JAR_FILE
ADD target/${JAR_FILE} /zrxjava/app.jar
ENTRYPOINT ["java", "-jar", "/zrxjava/app.jar"]