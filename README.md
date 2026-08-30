# FinCore – Digital Banking Management System

> A Java-based banking web application for managing customer accounts, balances, deposits, withdrawals, and transaction history.

## 💻 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- HTML5
- CSS3
- JavaScript
- Maven

## ✨ Features

- 👤 Account registration
- 🔐 Customer login and logout
- 📊 Banking dashboard
- 💰 Balance display
- ➕ Deposit money
- ➖ Withdraw money
- 📜 Transaction history
- 🏦 Account management
- 💾 MySQL database persistence

## 🧠 OOP Concepts

### Encapsulation
Private fields with controlled access through getters and setters.

### Inheritance
Specialized account types can extend a common account model.

### Polymorphism
Common account behavior can be extended or overridden by specialized account types.

### Abstraction
Service and repository layers separate business logic from database operations.

## 🔄 Application Flow

```text
Customer
   ↓
Register / Login
   ↓
Banking Dashboard
   ↓
View Balance
   ├── Deposit
   ├── Withdraw
   └── Transaction History
```

## 🏗️ Project Architecture

```text
Frontend
HTML + CSS + JavaScript
          │
          ▼
Spring Boot Controllers
          │
          ▼
Service Layer
          │
          ▼
Spring Data JPA
          │
          ▼
MySQL Database
```

## 📁 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com.bank/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── entity/
│   │       └── config/
│   │
│   └── resources/
│       ├── static/
│       └── application.properties
│
└── test/
```

## ⚙️ Setup

### Prerequisites

Install:

- JDK 17
- Maven
- MySQL
- Git

### 1. Clone the repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd fincore-digital-banking-system
```

### 2. Create the database

Open MySQL Workbench and run:

```sql
CREATE DATABASE bankdb;
```

### 3. Configure MySQL

Open:

```text
src/main/resources/application.properties
```

Configure your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

> Do not commit your real MySQL password to GitHub. Keep credentials local or use environment variables.

### 4. Run the application

```bash
mvn spring-boot:run
```

### 5. Open the website

```text
http://localhost:8080
```

## 📌 Project Highlights

FinCore demonstrates practical experience with:

- Java Object-Oriented Programming
- Spring Boot application development
- REST/web application architecture
- Spring Data JPA
- Hibernate
- MySQL database integration
- Frontend and backend integration
- Layered architecture

## 🔮 Future Enhancements

- Spring Security and JWT authentication
- Role-based access control
- Money transfer between accounts
- PDF bank statements
- Email transaction notifications
- Admin dashboard
- Account freeze/unfreeze
- Docker deployment
- Unit and integration testing

## ⚠️ Disclaimer

This is an educational and portfolio project. It is not a production banking application and should not be used for real financial transactions or sensitive banking data.

## 👩‍💻 Author

**Janani B.**

B.Tech Information Technology

GitHub: https://github.com/Jananiboominathan
