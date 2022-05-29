# todo-app REST API using Spring Boot 2.5.14, Java 11 and H2 Database 

## Compile and start

```bash
./compileAndRunToDoApp.sh
```
To start app in debug mode

```bash
DEBUG=true ./compileAndRunToDoApp.sh
```

To start app in different port

```bash
./compileAndRunToDoApp.sh --server.port=9090
```

## swagger-ui

```
http://localhost:8080/swagger-ui.html
```

## h2-console

```
http://localhost:8080/h2-console
```

## Spring Security

Spring security can be enabled and disabled using property 
```
todo.app.security.enabled
```
As Spring security implementation not completed, by default this property set to false  

