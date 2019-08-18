# database connection properties 
Edit `resources/application.properties` file for application configuration
```
server.port = 8080
spring.mvc.servlet.path = /api/v1/downtime

spring.datasource.url = jdbc:mysql://127.0.0.1:3306/rhb_downtime?useSSL=false&serverTimezone=Asia/Kuala_Lumpur
spring.datasource.username = admin
spring.datasource.password = Pa55word!
```

# How to access the service
## All APIs protected by basic auth with login information below:
```
Username: admin
Password: Pa55word!
```

## Available endpoints
| Method | Endpoint                       | Description                                 |
|--------|--------------------------------|---------------------------------------------|
| GET    | /api/v1/downtime/serverDate    | Get server date                             |
| POST   | /api/v1/downtime/adhoc         | [C]rud - Create Adhoc record                |
| GET    | /api/v1/downtime/adhoc         | c[R]ud - GET all adhoc records              |
| GET    | /api/v1/downtime/adhoc/deleted | c[R]ud - GET all soft-deleted adhoc records |
| GET    | /api/v1/downtime/adhoc/{id}    | c[R]ud - GET single adhoc record by id      |
| PUT    | /api/v1/downtime/adhoc/{id}    | cr[U]d - Update single adhoc record by id   |
| DELETE | /api/v1/downtime/adhoc/{id}    | cru[D] - Delete single adhoc record by id   |


# Advanced Notes

### starting mysql docker
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:latest
```

### entering mysql client
```
mysql -u root -p -h 127.0.0.1
```

### run widfly on docker
```
docker build -t wildfly-springboot-app .
docker run -p 8080:8080 -it wildfly-springboot-app
```