# Momentum Spring 

- mvn spring-boot:run

- mvn clean package -U
- java -jar target/spring-boot-exercise-0.0.1-SNAPSHOT.jar

### Preparing docker build

- mvn clean package -U
- docker build -t momentum/spring-boot .
- docker run --rm --name momentum-spring-boot -p 8080:8085 -it  momentum/spring-boot

### Swagger

Import in postman http://localhost:8085/api/swagger.json 
Browser http://localhost:8085/swagger-ui.html
