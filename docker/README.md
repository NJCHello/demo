#### jar包制作镜像
* [jar包制作成镜像]docker build -t registry:5000/demo/demo-web:1.0-20240101-1 .
* [保存镜像成tar包]docker save registry:5000/demo/demo-web:1.0-20240101-1 -o demo-web-20240101-1.tar
* [加载镜像到服务器]docker load -i demo-web-20240101-1.tar
* [推送镜像到仓库]docker push registry:5000/demo/demo-web:1.0-20240101-1
#### k8s给pod替换镜像
* [替换新镜像]kubectl set image deploy demo-web demo-web=registry:5000/demo/demo-web:1.0-20240101-1 -n demo
* [查看pod]kubectl get pod -n demo
* [修改pod数量]kubectl scale deploy demo-web --replicas=1 -n demo
* [查看pod日志]kubectl logs -f demo-web-86cf5fcddd-ws74s -n demo
#### jar包后台启动
* [查看端口占用进程]lsof -i:8080
* [查看java程序]jps -l
* [关闭java程序]kill -9 xxxx
* [后台启动]nohup java -jar demo-web-1.0.jar >web.out 2>&1 &