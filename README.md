# Momentum Java Exercise 

The following commands will help starting the development environment:

### IDE
Jetbrains IntelliJ

The following commands can be used to start up the project

- mvn spring-boot:run

- mvn clean package -U
- java -jar target/spring-boot-exercise-0.0.1-SNAPSHOT.jar

### Preparing docker build

- mvn clean package -U
- docker build -t momentum/spring-boot .
- docker run --rm --name momentum-spring-boot -p 8080:8085 -it  momentum/spring-boot

### Swagger

Import in postman url: http://localhost:8085/api/swagger.json 
Swagger ui url: http://localhost:8085/swagger-ui.html
