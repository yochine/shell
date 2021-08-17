
> 内存占用前十命令
> ps auxw|head -1;ps auxw|sort -rn -k4|head -10


> minio 19090
docker run -p 19090:19090 --name minio \
-d --restart=always \
-e "MINIO_ACCESS_KEY=minioadmin" \
-e "MINIO_SECRET_KEY=minioadmin" \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
-v /mnt/minio/data:/data \
-v /mnt/minio/config:/root/.minio \
minio/minio server --address 0.0.0.0:19090 /data


> portainer 18000 19000
docker run -d -p 18000:18000 -p 19000:19000 \
--name=portainer --restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /mnt/portainer/data:/data \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
portainer/portainer --bind=":19000" --tunnel-port="18000"

>  nacos 18848
docker  run \
--name nacos -d \
-p 18848:8848 \
--restart=always \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e JVM_XMN=512m \
-e MODE=standalone \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=1.14.137.58 \
-e MYSQL_SERVICE_PORT=13306 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=root@123 \
-e MYSQL_SERVICE_DB_NAME=nacos \
-v /etc/localtime:/etc/localtime:ro \
-v /mnt/nacos/logs:/home/nacos/logs \
-v /mnt/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties \
nacos/nacos-server

> nexus 18081 18082
docker run -d --name nexus \
--restart=always \
--privileged=true \
-p 18081:18081 -p 18082:18082 \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
-v /mnt/nexus/data:/nexus-data \
sonatype/nexus3 


docker cp nexus:/opt/sonatype/nexus/bin/nexus.vmoptions /mnt/nexus/bin/nexus.vmoptions

docker cp /mnt/nexus/bin/nexus.vmoptions.old nexus:/opt/sonatype/nexus/bin/nexus.vmoptions.old

docker cp /etc/localtime redis:/etc/localtime


> mysql 13306
docker run -p 13306:13306 --name mysql-5.7 \
-v /mnt/mysql/conf:/etc/mysql \
-v /mnt/mysql/logs:/var/log/mysql \
-v /mnt/mysql/data:/var/lib/mysql \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
-e MYSQL_ROOT_PASSWORD=root@123 \
-d --restart=always \
--default-time_zone='+8:00'\
mysql:5.7

> redis 16378:16379
docker run -p 16379:16379 --name redis \
-v /mnt/redis/redis.conf:/etc/redis/redis.conf \
-v /mnt/redis/data:/data \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
-d --restart=always  \
redis redis-server /etc/redis/redis.conf  --requirepass root@123


> nginx


> drone 13080

docker run \
  -v /mnt/drone/data:/data \
  -v /etc/timezone:/etc/timezone:ro \
  -v /etc/localtime:/etc/localtime:ro \
  -e DRONE_GITHUB_CLIENT_ID=bd5869573540c0122dab \
  -e DRONE_GITHUB_CLIENT_SECRET=c00cde39860c48036afccbcfaecf5ba64194f263 \
  -e DRONE_RPC_SECRET=079d3a77ba05999cc51c0f2c77476a8d \
  -e DRONE_SERVER_HOST=1.14.137.58:13080 \
  -e DRONE_SERVER_PROTO=http \
  -e DRONE_USER_CREATE=username:shell,admin:true \
  -e TZ="Asia/Shanghai" \
  -p 13080:80 \
  --restart=always \
  --detach=true \
  --name=drone \
  drone/drone:2

 > drone-runner-docker 13000

docker run -d \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /etc/timezone:/etc/timezone:ro \
  -v /etc/localtime:/etc/localtime:ro \
  -e DRONE_RPC_PROTO=http \
  -e DRONE_RPC_HOST=1.14.137.58:13080 \
  -e DRONE_RPC_SECRET=079d3a77ba05999cc51c0f2c77476a8d \
  -e DRONE_RUNNER_CAPACITY=2 \
  -e DRONE_RUNNER_NAME=runner-docker \
  -e TZ="Asia/Shanghai" \
  -p 13000:13000 \
  --restart always \
  --name drone-runner \
  drone/drone-runner-docker:1 


  > canal  11111

docker run --name canal -p 11111:11111 -d \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
-v /mnt/canal/instance.properties:/home/admin/canal-server/conf/example/instance.properties \
-v /mnt/canal/canal.properties:/home/admin/canal-server/conf/ 
canal.properties \
canal/canal-server


> keycloak 18080
docker run -p 18080:8080 --name keycloak \
-e KEYCLOAK_USER=admin \
-e KEYCLOAK_PASSWORD=admin \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
--restart always \
-d jboss/keycloak

> grafana 13001

docker run -p 13001:3000 \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
--restart always \
--name grafana \
-d grafana/grafana

> prometheus 19091

docker run -p 19091:9090 --name prometheus \
-v /mnt/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml \
-v /etc/timezone:/etc/timezone:ro \
-v /etc/localtime:/etc/localtime:ro \
--restart always \
-d prom/prometheus



