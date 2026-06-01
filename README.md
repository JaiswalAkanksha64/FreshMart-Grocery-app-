# FreshMart - Full Stack Grocery Application

A full-stack grocery shopping application with JWT authentication, cart management, and order processing.

## Tech Stack

**Backend:** Java 17, Spring Boot 3, Spring Security, JWT, Spring Data JPA, MySQL, Maven

**Frontend:** React.js, React Router DOM, Axios, Context API

## Features

- User registration and login with JWT authentication
- Browse and search products by category
- Add to cart, update quantity, remove items
- Manage delivery addresses
- Place orders (Cash on Delivery)
- View order history
- Admin panel for managing products and orders

## Getting Started

### Prerequisites
- Java 17+, Node.js 18+, MySQL 8+, Maven

### Backend Setup

1. Create MySQL database
```sql
CREATE DATABASE grocerydb;
```

2. Create `application-local.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
app.jwt.secret=your_jwt_secret_key
```

3. Run: `mvn spring-boot:run` → `http://localhost:8080`

### Frontend Setup
```bash
npm install
npm start
```
Runs on `http://localhost:3000`

## Security
- JWT stateless authentication
- BCrypt password encryption
- Role-based access (USER / ADMIN)
- Protected frontend routes