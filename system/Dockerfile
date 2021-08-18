FROM zrxjava-docker.pkg.coding.net/zhong-cloud/rep/java-base:1.0.0

COPY target/shell-api.jar  /opt
# Image创建容器时的初始化内存，最大内存，及启动时使用的profile. -c为清除以前启动的数据
ENTRYPOINT ["sh","-c","./start.sh shell-api.jar"]
