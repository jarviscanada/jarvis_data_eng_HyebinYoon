# Introduction
The Java Database Connectivity (JDBC) application was developed to create a connection between a Java application and 
to PostgreSQL RDBMS using Java library, Data Access Object (DAO), Data Transfer Object (DTO) and Repository design pattern

### Technologies Used:
- Core Java
- Maven
- JDBC libraries
- IntelliJ IDE
- Docker
- PostgresSQL
- DBeaver
- DAO, DTO and repository design patterns 

## ER Diagram
![jdbc_er_diagram](./assets/jdbc_er_diagram.png?raw=true)
###### figure1: ER diagram generated using DBeaver

## Design Patterns

### Data Access Object (DAO)
The Data Access Object design pattern is an abstract concept that allows programmers to use the JDBC libraries to separate the business layer from the persistence layer.
It is a simple class or interfaces that allows programmers to perform CREATE, READ, UPDATE, and DELETE data to the database instead of directly performing
the SQL queries. The main advantage of using DAO is that it conceals all the low-level implementation so that the business logic and database are decoupled.
### Repository 
The Repository pattern is a popular Java persistence pattern. Simply repositories are classes or components encapsulating the logic required to access data sources. 
They centralize standard data access functionality, providing better maintainability and decoupling the infrastructure 
or technology used to access databases from the domain model layer.

# Test
The application was tested with a 1000-sample database using SQL scripts. 
The SQL scripts were tested using Docker PostgresSQL container using a postgres:9.6-alpine image.
The ``JDBCExecutor`` class executes ``create``, ``findbyID``, ``update``, and ``delete``which is passed to the DAO class
that operates CRUD operation to manipulate the data in the database. Also utilized PostgresSQL CLI to see if the operations were successfully done and used
DBeaver to check every table had the correct relationship with each other.