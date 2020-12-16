# Hexagonal Architecture Demonstration

## Functional presentation

This really simple project was developed to experiment hexagonal architecture using java and spring framework.
Functionally, it manages driving licences saved in a postgre relational database, and exposes some REST. These REST
endpoints allow clients to manage driving licences.

## Technical stack

- Java 11
- Spring boot 2.4.0
- Vavr
- Lombok
- Spring data jpa
- PostgreSQL

## Application breakdown

### The domain module

This is the most important module of the application. It contains all the business logic. In our case, the business
logic means : driving licences management.

### The client module

This module contains one or more sub-modules, designed to respond to interactions with users. In our case, only one
module was created to manages the REST part. However we could imagine to create another one to consume a Kafka topic for
example.

#### The rest-adapter module

This module contains all the technical code for exposing and interacting with REST resources. It also contains the REST
API documentation.

### The server module

This module contains one or more modules for interacting with all the external services necessary for the operation of
the application. In our case we have only one. But we could imagine other modules. For example, a module to connect to a
flipping feature system.

#### The postgres-adapter module

This module contains all the technical code to interact with the postgres database.