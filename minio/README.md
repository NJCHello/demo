# minio
* [docker安装](https://min.io/docs/minio/container/index.html)
## docker安装
  docker run -d \
  -p 9080:9080 \
  -p 9000:9000 \
  --name minio-server \
  -v /data/minio/data:/mnt/data \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin" \
  minio/minio:latest server /data --console-address ":9080"
## 安装后访问
http://localhost:9080/browser
## 账号密码
minioadmin/minioadmin