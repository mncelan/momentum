# Momentum Java Exercise 

The following commands will help starting the development environment:

### IDE
Jetbrains IntelliJ

#### Branches

- **develop** is the main branch for this project

The following commands can be used to start up the project

- mvn spring-boot:run

- mvn clean package -U
- java -jar target/spring-boot-exercise-0.0.1-SNAPSHOT.jar

### Preparing docker build

- mvn clean package -U
- docker build -t momentum/spring-boot .
- docker run --rm --name momentum-spring-boot -p 8080:8085 -it  momentum/spring-boot

###  Running Prometheus using Docker

- docker run -d --name=prometheus -p 9090:9090 -v <Copy Paste Absolute Path of prometheus.ym>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml

#### Postman import
Import using this url: http://localhost:8085/api/swagger.json 

#### Swagger
Swagger ui url: http://localhost:8085/swagger-ui.html