
# Task Management Backend

A secure Task Management REST API built with Spring Boot, featuring JWT-based authentication, user registration, and full CRUD operations for user-specific tasks.

---

## Table of Contents

- [Features](#features)  
- [Technology Stack](#technology-stack)  
- [Getting Started](#getting-started)  
- [Configuration](#configuration)  
- [API Endpoints](#api-endpoints)  
- [Authentication](#authentication)  
- [Swagger API Documentation](#swagger-api-documentation)  
- [Contributing](#contributing)  
- [License](#license)

---

## Features

- User registration and login with JWT authentication  
- Password encryption with BCrypt  
- Create, Read, Update, Delete (CRUD) operations for tasks  
- Tasks are associated with authenticated users  
- Secure endpoints with role-based access (ROLE_USER)  
- Cross-Origin Resource Sharing (CORS) configured for frontend integration  
- Health check endpoint  
- Swagger/OpenAPI integration for API documentation  

---

## Technology Stack

- Java 17+  
- Spring Boot  
- Spring Security  
- Spring Data JPA (Hibernate)  
- MySQL  
- JWT (JSON Web Token)  
- Lombok  
- Swagger (springdoc-openapi)  
- Jakarta Validation  
- Maven  

---

## Getting Started

### Prerequisites

- Java 17 or later  
- MySQL Server running locally or remotely  
- Maven  

### Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/task-management-backend.git
cd task-management-backend
````

2. Configure your MySQL database and update connection details in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Build and run the application:

```bash
./mvnw spring-boot:run
```

The backend will be available at `http://localhost:8080`.

---

## Configuration

The app uses JWT for stateless authentication. Configure these properties in `application.properties`:

```properties
jwt.secret=YOUR_BASE64_ENCODED_SECRET_KEY
jwt.expiration-ms=86400000  # Token expiration in milliseconds (1 day)
```

Use a strong secret key for production environments.

---

## API Endpoints

| Method | Endpoint         | Description                      | Auth Required? |
| ------ | ---------------- | -------------------------------- | -------------- |
| POST   | `/auth/register` | Register a new user              | No             |
| POST   | `/auth/login`    | Login and receive JWT token      | No             |
| GET    | `/health`        | Health check endpoint            | No             |
| POST   | `/tasks`         | Create a new task                | Yes            |
| GET    | `/tasks`         | Get all tasks for logged-in user | Yes            |
| PUT    | `/tasks/{id}`    | Update a task by ID              | Yes            |
| DELETE | `/tasks/{id}`    | Delete a task by ID              | Yes            |

---

## Authentication

* Authentication uses JWT tokens passed in the `Authorization` header as `Bearer <token>`.
* Passwords are stored encrypted using BCrypt.
* Users can only access and manage their own tasks.

---

## Swagger API Documentation

Swagger UI is available to explore and test the API:

```
http://localhost:8080/swagger-ui.html
```

---

## CORS Configuration

Configured to allow requests from `http://localhost:5500` (typical for frontend dev servers like Live Server or React/Vue apps).

---

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to check issues page.

---


## Contact

Developed by [Martha Abhinav](mailto:abhinavmartha555@gmail.com)

---

**Happy task managing!** 🚀

```

---

If you want, I can also help you generate a `LICENSE` file or tweak this further! Would you like me to add instructions for frontend integration or deployment tips?
```
