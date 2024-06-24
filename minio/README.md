# minio
## docker安装
  docker run -d \
  -p 9008:9008 \
  -p 9090:9090 \
  --name minio-server \
  -v /data/minio/data:/mnt/data \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin" \
  minio/minio:latest server /data --console-address ":9008"
## 安装后访问
http://localhost:9008/browser
## 账号密码
minioadmin/minioadmin