# shell服务搭建指南

本次演示指南背景环境

- 腾讯云服务器cvm
- centos8
- docker

## 依赖的镜像

- mysql
- redis
- node
- nginx

## docker

安装

`curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun`

启动

`systemctl start docker`

开放远程连接端口(供后端采用docker-maven一键部署)

`vi /usr/lib/systemd/system/docker.service`

```
[Service]
Type=notify
# the default is not to use systemd for cgroups because the delegate issues still
# exists and systemd currently does not support the cgroup feature set required
# for containers run by docker
ExecStart=
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
```

`systemctl daemon-reload && systemctl restart docker`

## mysql

1. 拉取镜像，这里拉取最新的msysql

   `docker pull mysql`

2. 宿主机创建对应配置文件
   - `mkdir /mydata/soft/mysql/conf -p `
   - `cd /mydata/soft/mysql/conf `
   - `touch my.cnf`  修改port端口为3307
   

   ```
   [mysqld]
   pid-file        = /var/run/mysqld/mysqld.pid
   socket          = /var/run/mysqld/mysqld.sock
   datadir         = /var/lib/mysql
   secure-file-priv= NULL
   port=3307
   # Custom config should go here
   !includedir /etc/mysql/conf.d/




3.  启动镜像,进行挂载，绑定端口

​      `docker run -dp 3307:3307 --name mysql  --restart=always -v /mydata/soft/mysql/my.cnf:/etc/mysql/my.cnf -e MYSQL_ROOT_PASSWORD="xxxxx"  mysql --net=host ` 

4. 修改密码规则

   mysql8.0 之前的版本中加密规则是mysql_native_password,而在mysql8之后,加密规则是caching_sha2_password,

   `ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'xxxxx';`

   `flush privileges;`

5. 远程登录执行需要的脚本

## redis

拉取镜像

`docker pull redis`

创建挂载目录

`mkdir /mydata/soft/redis/data -p` 

启动

`docker run -dp 6379:6379 --name redis --restart=always --network=host  -v /mydata/soft/redis/data:/data redis`

## ngxin

拉取

`docker pull nginx`

创建挂载目录以及前端静态目录

`mkdir /mydata/soft/ngxin/conf -p`

`mkdir /mydata/soft/ngxin/logs -p`

`mkdir /mydata/soft/shell-vue/dist -p`

配置nginx.conf

   ```
user  nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}

http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';
    
    access_log  /var/log/nginx/access.log  main;
    
    sendfile        on;
    #tcp_nopush     on;
    
    keepalive_timeout  65;
    
    #gzip  on;
        
        proxy_redirect          off;
        proxy_set_header        Host $host;
        proxy_set_header        X-Real-IP $remote_addr;
        proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        client_max_body_size    10m;
        client_body_buffer_size   128k;
        proxy_connect_timeout   5s;
        proxy_send_timeout      5s;
        proxy_read_timeout      5s;
        proxy_buffer_size        4k;
        proxy_buffers           4 32k;
        proxy_busy_buffers_size  64k;
        proxy_temp_file_write_size 64k;
        
        server {
                listen 8085;
                server_name  localhost;
                location /api {
                        proxy_pass      http://127.0.0.1:9091/shell;
                        proxy_set_header        Host 127.0.0.1;
                        proxy_set_header        X-Real-IP $remote_addr;
                        proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
                }
                location / {
                        root  /opt/shell/vue/dist;
                        index  index.html;
                }
        }
}

   ```



启动

`docker run -it -d --name shell-vue --restart=always  -v /mydata/soft/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v /mydata/soft/nginx/logs:/var/log/nginx/ -v /mydata/soft/shell-vue/dist:/shell/vue/dist --privileged --net=host nginx`

## 后端服务

打包推送

```
 <build>
        <finalName>shell</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <!-- 跳过单元测试 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>${docker.maven.plugin.version}</version>
                <!--将Docker命令绑定到Maven阶段,也就是使用maven的package命令打包时自动构建镜像-->
                <executions>
                    <execution>
                        <id>build-image</id>
                        <phase>package</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <buildArgs>
                        <JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                    <forceTags>true</forceTags>
                    <rm>true</rm>
                    <imageName>zrxjava/${project.build.finalName}:${project.version}</imageName>
                    <!--强制覆盖-->
                    <forceTags>true</forceTags>
                    <dockerHost>${docker.host}</dockerHost>
<!--                    <dockerDirectory>${project.basedir}</dockerDirectory>-->
                    <baseImage>java:8</baseImage>
                    <volumes>
                        <volume>/mydata/soft/shell-admin/data</volume>
                    </volumes>
                    <entryPoint>["java", "-jar", "-Dspring.profiles.active=prod","/opt/soft/${project.build.finalName}.jar"]</entryPoint>
                    <resources>
                        <resource>
                            <targetPath>/opt/soft/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
        </plugins>
    </build>
```



启动

`docker run -dp 9091:9091 --name shell-admin --restart=always -v /mydata/soft/shell-admin/logs:/logs --net=host zrxjava/shell:0.0.1-SNAPSHOT`



## portainer

拉取镜像

`docker pull portainer/portainer`

启动

`docker run -p 9000:9000 -p 8000:8000 --name portainer \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /mydata/portainer/data:/data \
-d portainer/portainer`

> 修改默认启动端口
> --bind=":19000" --tunnel-port="18000"
> http端口是19000 ，ws端口是 18000 。必须两个端口都可以访问才行