
# MagnusMovie Project



"MagnusMovie" is a backend part of the endProject,  for the Full Stack Java Developer course at [HackerU](https://www.hackeru.co.il/). 

The project is a comprehensive movie information system, designed as a web application using Spring Boot and Java. It features a robust backend architecture with several interconnected entities, allowing for detailed tracking and management of movies, reviews, users, and roles. 





## Appendix

Project Overview
This project is a sophisticated web application for movie information management, implemented using a three-tier architecture in Spring Boot. This design pattern separates the application into distinct layers, enhancing maintainability, scalability, and separation of concerns.since working with spring boot involves the use of patterns, here are some that were used:

### Three-layered Architecture - pattern
Presentation Layer:

#### Controller:
 The user interface of the application. In the context of this project, it refers to the part of the application that interacts with the end-users.
Responsibilities: User interaction, displaying data, and sending user commands to the server.
Business Logic Layer (Service Layer):

#### Service: 
The core of the application, responsible for executing specific business rules associated with the request.
Responsibilities: Processing data received from the presentation layer, applying business rules, interacting with the database layer, and preparing data to be sent back to the client.
Data Access Layer (Repository Layer):

#### Repository:
 Handles data persistence and retrieval.
Responsibilities: Communicating with the database to store and retrieve data. This layer uses Spring Data
Core
Movie: Represents
Review: Stores
User: Man
Role: Defines
Each

#### Features:
CRUD Operations: Comprehensive
User Authentication and Authorization: Secure
Data Validation: Ens
API Integration: Ex

 #### Dependency Injection (DI):

Spring manages the creation and wiring of objects instead of developers doing it manually. This provides better modularity and eases testing.

#### Singleton Pattern:

Most Spring Beans are singletons by default. This means the Spring container creates a single instance of a bean, which is then shared across the application. This approach is efficient in terms of resource management.

#### Repository Pattern:

Used for abstracting and encapsulating all access to the data source. The repository manages the data operations and allows for cleaner separation of concerns.

#### DTO Pattern (Data Transfer Object):

Employed for transferring data between different layers of the application. DTOs help in isolating the domain model from the client code, ensuring data security and simplifying data transmission.

#### Exception Handling:

Spring Boot provides robust support for a centralized exception handling mechanism. Custom exceptions and annotations like @ControllerAdvice and @ExceptionHandler are used for handling different types of exceptions consistently.






## Run Locally

Clone the project

```bash
  git clone https://github.com/Valeras17
```


Install dependencies

```bash
  maven in pom.xml 
```
check application.properties

```bash
  config database and security
```

Start the server

```bash
   start run
```


# Deployment

### To deploy this project you may need some dependencies:
## you can find them in - pom.xml

### Technical requirements and environment setup: jdk 18, maven 3.0
 
#### Spring Boot Starter Data JPA
Facilitates database operations through Java Persistence API.

### Spring Boot Starter Validation
Provides support for data validation.

### Spring Boot Starter Web
Used for building web applications, including RESTful applications.

### Spring Boot DevTools
Offers development tools such as automatic application restart.

### MySQL Connector/J
JDBC driver for MySQL database connectivity.

### Spring Boot Configuration Processor
Assists in generating metadata for Spring Boot configuration properties.

### Lombok
A library that automatically plugs into your editor and build tools, simplifying Java code by reducing boilerplate.

### ModelMapper
An object mapping library that simplifies the process of mapping objects from one design to another.

### Spring Boot Starter Security
Adds security and authentication features to a Spring Boot application.

### JJWT API, JJWT Impl, and JJWT Jackson
Libraries for creating and parsing JSON Web Tokens (JWTs), used for secure authentication and authorization.

### SpringDoc OpenAPI Starter Webmvc UI and SpringDoc OpenAPI Starter Common
Used for generating OpenAPI/Swagger documentation for Spring Boot RESTful services and their security integration.


## Documentation

[Documentation - localhost:8080/swagger-ui](http://localhost:8080/swagger-ui/index.html)


## Running Tests

To run tests, http requests, you need go to folder resources/rest/

### auth.rest - file for test requests login / singup

```bash
 POST http://localhost:8080/api/v1/auth/signup
```

### movie.rest - file for test requests movies CRUD

```bash
 POST http://localhost:8080/api/v1/movies
```

### review.rest - file for test requests review CRUD

```bash
 POST http://localhost:8080/api/v1/movies/1/reviews
```

## Usage/Examples

```Spring Boot
### signup a new User:
POST http://localhost:8080/api/v1/auth/signup
Content-Type: application/json


{
  "email": "john@gmail.com",
  "username": "john",
  "password": "Password1!"
}
```

```Spring Boot
### sign user in:
POST http://localhost:8080/api/v1/auth/signin
Content-Type: application/json

{
  "username": "john",
  "password": "Password1!"
}

```

```Spring Boot
### Add new Movie :
POST http://localhost:8080/api/v1/movies
Content-Type: application/json
Authorization: Basic john Password1!

{
  "title": "batman 8 team",
  "poster": "posterurl",
  "url": "url url!",
  "year": "1999",
  "genre": "action",
  "backdropScreen": "1999"
}

```

```Spring Boot
### Delete movie by id :
DELETE http://localhost:8080/api/v1/movies/52
Content-Type: application/json
Authorization: Basic john Password1!

```

```Spring Boot
### Add new review :
POST http://localhost:8080/api/v1/movies/1/reviews
Content-Type: application/json
Authorization: Basic john Password1!

{
  "textReview": "123!",
  "rating": 5
}

```


## Acknowledgements

 -  I would like to express my heartfelt gratitude to everyone at HackerU, including the team members, lecturers, and organizers,  for   their guidance and support throughout this course. A big thank you to everyone involved in making this learning journey possible and enriching.




## Authors

- [@Valeriy Gordienko](https://github.com/Valeras17)

