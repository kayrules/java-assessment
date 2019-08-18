# starting mysql docker
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:latest
```

# entering mysql client
```
mysql -u root -p -h 127.0.0.1
```

# run widfly on docker
```
docker build -t wildfly-springboot-app .
docker run -p 8080:8080 -it wildfly-springboot-app
```
