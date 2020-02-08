# City ATM Rest Service with Spring Boot, Apache Camel, REST, Spring Security, Spring Data JPA,Bootstrap, MockMvc, JDK 8

# Overview
Java web application that provide a REST API to view a list of ATMs in the Dutch cities.
The web application invoke an external service to gather the data.
The data will be displayed by an html client

# Technology stack
- Spring Boot 2 (**doesn't work if deployed on tomcat 7 webapp** )
- Spring Security
- Apache camel
- Spring Data JPA
- Hibernate
- HSQL (In Memory)
- JSP 
- Bootstrap
- Spring MockMvc, Mockito and JUnit 5
- Maven 3
- NetBeans 8.2
- Jdk 8

# Description

### JPA and Hibernate Entities
JPA Entity is defined with @Entity annotation, represent a table in your database.

### Spring Data JPA Repositories
Spring Data JPA contains some built-in Repository implemented common functions to work with database such as findOne, findAll, etc.

### Spring Security
To implement login/authentication with Spring Security, we implement org.springframework.security.core.userdetails.UserDetailsService interface
We create SecurityService to provide current logged-in user and auto login user after registration
We don't define /login POST controller, it is provided by Spring Security

### Rest endpoint

Spring Boot REST controller calls the Camel route.
Camel through producerTemplate calls the external service to retrieve the data.
Spring rest controller will expose the API to the front end

### Junit Test
@SpringExtension is been introduced in Spring 5.
Is used to integrate Spring TestContext with JUnit 5 Jupiter Test. 
For the tests I used Spring MockMvc and Mockito. 
MockMvc simulates the calls to the spring controller
Mockito simulates the calls to the camel service


### Installation
To start the application: 
```sh 
mvn clean spring-boot:run
http://localhost:8080/
```

To perform the tests: 
```sh 
mvn test
```
To create war: 
```sh 
mvn package
The file will be created under the target directory
```





