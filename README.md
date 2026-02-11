# 🎫 Ticket Management System (Backend)

A secure, backend-focused Ticket Management System built using Spring Boot, Spring Security, and JPA.

This project simulates a realistic internal IT support system where users can raise tickets and access is strictly controlled based on roles and ownership.

The system is designed with clean architecture, security best practices, and clear separation of concerns.

---

## Project Objective

The goal of this project is to build a production-style backend system that demonstrates:

- Secure authentication using JWT
- Role-based access control (RBAC)
- Ownership-based data protection
- Clean layered architecture
- Proper exception handling
- Transaction integrity

This project focuses entirely on backend system design and security.

---


### 🧱 Architecture Overview

The application follows a layered architecture:

Controller → Service → Repository → Database
- DTOs are used to separate persistence entities from API responses
- Security logic is handled via Spring Security
- Business rules are enforced at the service layer

### 🔐 Security Implementation
### ✅ Authentication
- JWT-based authentication
- Short-lived access tokens
- Refresh token mechanism
- Custom authentication flow using UserDetailsService

### ✅ Authorization
Role-Based Access Control (RBAC)
- Roles: USER, AGENT, ADMIN
- Endpoint-level access restrictions
- Ownership-based checks (user can access own data; admin can access all)
### ✅ Password Security
- Passwords hashed using BCrypt before persistence
- No plain-text password storage
  
### ✅ Error Handling
- Custom exception hierarchy
- Centralized global exception handling
- Proper HTTP status codes (403, 404, etc.)
  
---

### 📦 Core Features
## 👤 User Management

- User registration
- Role assignment
- Secure user retrieval with access control

## 🎫 Ticket Management

- Ticket creation
- Ticket update
- Ownership validation
- Role-based restrictions

## 🧾 Audit Support

- createdAt, updatedAt, createdBy fields
- Entity lifecycle tracking

## 🔁 Transaction Management

- Service-layer transaction integrity
- Atomic database operations

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- Hibernate / JPA  
- MySQL  
- Maven
- Spring Security
- Postman (API testing)

---

## 📁 Current Project Structure
```text
ticket-management
|
|-- controller
|-- service
|-- repository
|-- entity
|-- entityDto
|-- security
|-- exception
|-- TicketManagementApplication.java

```
---
### 🔄 Example Security Flow
- User logs in and receives JWT access + refresh token.
- Access token is sent in Authorization header.
- JWT filter validates token.
- Spring Security sets Authentication in SecurityContext.
- Service layer enforces:
  - Role-based access
  - Ownership validation
  - Unauthorized access returns HTTP 403.

---
---
📊 Current Status

- ✅ Authentication and Authorization implemented
- ✅ RBAC working
- ✅ Ownership-based access control verified
- ✅ DTO separation
- ✅ Transaction management
- ✅ Custom exception handling

🚧 Pagination, filtering, deployment, and infrastructure improvements planned.
---
