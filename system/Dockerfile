FROM java:8
MAINTAINER zrxjava
ENV TZ=Asia/Shanghai
ARG JAR_FILE
COPY /target/${JAR_FILE}  /opt/soft/shell.jar
# Image创建容器时的初始化内存，最大内存，及启动时使用的profile. -c为清除以前启动的数据
ENTRYPOINT ["java", "-jar", "/opt/soft/shell.jar","--spring.profiles.active=prod"]
