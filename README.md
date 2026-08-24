# Bank Management System

Java OOP based Bank Management System website using:

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- HTML
- CSS
- JavaScript

## OOP concepts

- Encapsulation: private fields + getters/setters
- Inheritance: SavingsAccount extends Account
- Polymorphism: Account can be extended/overridden
- Abstraction: service and repository layers

## Features

- Account registration
- Login
- Dashboard
- Balance display
- Deposit
- Withdraw
- Transaction history
- Logout

## Setup

1. Install JDK 17.
2. Install MySQL and Maven.
3. Create the database:

```sql
CREATE DATABASE bankdb;
```

4. Open:
`src/main/resources/application.properties`

5. Replace:

`YOUR_MYSQL_PASSWORD`

with your MySQL root password.

6. Run:

```bash
mvn spring-boot:run
```

7. Open:

http://localhost:8080/

The project serves the website from Spring Boot's static resources.

## Note

This is a college/learning project. Do not use the authentication or plain-text password storage for a real banking application.
