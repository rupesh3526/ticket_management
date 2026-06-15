# 🎫 Ticket Management System (Backend)

A secure, backend-focused Ticket Management System built using Spring Boot, Spring Security, JPA, and AI-powered summarization via the Gemini API.

This project simulates a realistic internal IT support system where users can raise tickets and access is strictly controlled based on roles and ownership. The system is designed with clean architecture, security best practices, and clear separation of concerns.

---

## 🎯 Project Objective

The goal of this project is to build a production-style backend system that demonstrates:

- Secure authentication using JWT
- Role-based access control (RBAC)
- Ownership-based data protection
- Clean layered architecture
- Proper exception handling
- Transaction integrity
- Real-world LLM API integration (Google Gemini)

This project focuses entirely on backend system design, security, and AI integration.

---

## 🧱 Architecture Overview

The application follows a layered architecture:

```
Controller → Service → Repository → Database
                ↓
          Gemini AI API (external)
```

- DTOs are used to separate persistence entities from API responses
- Security logic is handled via Spring Security
- Business rules are enforced at the service layer
- AI summarization is triggered automatically at the service layer

---

## 🔐 Security Implementation

### ✅ Authentication
- JWT-based authentication
- Short-lived access tokens
- Refresh token mechanism
- Custom authentication flow using UserDetailsService

### ✅ Authorization — Role-Based Access Control (RBAC)
- Roles: USER, AGENT, ADMIN
- Endpoint-level access restrictions
- Ownership-based checks (user can access own data; admin can access all)

### ✅ Password Security
- Passwords hashed using BCrypt before persistence
- No plain-text password storage

### ✅ Error Handling
- Custom exception hierarchy
- Centralized global exception handling via `@ControllerAdvice`
- Proper HTTP status codes (400, 403, 404, 500)

---

## 📦 Core Features

### 👤 User Management
- User registration and login
- Role assignment (Admin only)
- Get user by ID
- Get all users
- Update user details
- Delete user

### 🎫 Ticket Management
- Create ticket with automatic AI summary generation
- Update ticket (full update)
- Update ticket status
- Delete ticket
- Get ticket by ID
- Get all tickets (paginated, sorted)
- Get tickets by status
- Get tickets by priority
- Regenerate AI summary for existing ticket

### 🤖 AI Integration (Google Gemini API)
- Auto-generates a 2-3 line intelligent summary on ticket creation
- Uses **Gemini 2.5 Flash-Lite** model (free tier — 1000 requests/day)
- Summary considers issue, description, priority, and status
- Called via **RestTemplate** (Spring's HTTP client)
- Graceful fallback if API is unavailable
- API key stored securely in system environment variables

### 💬 Comment Management
- Add comment to ticket
- Get all comments by ticket ID
- Update comment
- Delete comment

### 🧾 Audit Support
- `createdAt`, `updatedAt`, `createdBy` fields
- Entity lifecycle tracking via `@PrePersist` and `@PreUpdate`

### 🔁 Transaction Management
- Service-layer transaction integrity
- Atomic database operations via `@Transactional`

### 📄 Pagination & Filtering
- Paginated ticket listing with configurable page size
- Sorting by any field (asc/desc)
- Filter tickets by status and priority

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Core language |
| Spring Boot 3.4.4 | Application framework |
| Spring Security | Authentication & Authorization |
| JWT (jjwt) | Token-based auth |
| Hibernate / JPA | ORM & database operations |
| MySQL | Relational database |
| Maven | Build tool |
| RestTemplate | External API calls |
| Google Gemini API | AI-powered ticket summarization |
| Lombok | Boilerplate reduction |
| ModelMapper | DTO mapping |
| Postman | API testing |

---

## 📁 Project Structure

```text
ticket-management
│
├── controller
│   ├── TicketController.java
│   ├── UserController.java
│   ├── CommentController.java
│   ├── AdminController.java
│   └── AuthController.java
│
├── service
│   ├── TicketService.java
│   ├── UserService.java
│   ├── CommentService.java
│   └── impl/
│       ├── TicketServiceImp.java      ← includes Gemini AI integration
│       ├── UserServiceImp.java
│       ├── CommentServiceImpl.java
│       └── AuthServiceImpl.java
│
├── repository
├── entity
├── dto
│   └── response/
├── security
│   ├── SecurityConfig.java
│   ├── JwtFilter.java
│   └── JwtUtil.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── (custom exceptions)
│
└── TicketManagementApplication.java
```

---

## 🌐 API Endpoints

### Auth
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /auth/login | Public |
| POST | /auth/signup | Public |
| POST | /auth/refresh | Public |

### Tickets
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /ticket/createTicket | Authenticated |
| GET | /ticket/getTickets | Authenticated |
| GET | /ticket/getTicket/{id} | Authenticated |
| PUT | /ticket/updateTicket/{id} | Authenticated |
| PUT | /ticket/updateStatus/{id} | Authenticated |
| DELETE | /ticket/deleteTicket/{id} | Authenticated |
| GET | /ticket/getByStatus?status= | Authenticated |
| GET | /ticket/getByPriority?priority= | Authenticated |
| PUT | /ticket/regenerateSummary/{id} | Authenticated |

### Users
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /user/createUser | Authenticated |
| GET | /user/getUser/{id} | Authenticated |
| GET | /user/getAllUsers | Authenticated |
| PUT | /user/updateUser/{id} | Authenticated |
| DELETE | /user/deleteUser/{id} | Authenticated |
| GET | /user/whoami | Authenticated |

### Comments
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /comment/createComment | Authenticated |
| GET | /comment/getCommentsByTicketId/{id} | Authenticated |
| PUT | /comment/updateComment/{id} | Authenticated |
| DELETE | /comment/deleteComment/{id} | Authenticated |

### Admin
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /admin/tickets | ADMIN only |
| GET | /admin/updateRole | ADMIN only |

---

## 🔄 Security Flow

1. User logs in → receives JWT access token + refresh token
2. Access token sent in `Authorization: Bearer <token>` header
3. JWT filter validates token on every request
4. Spring Security sets Authentication in SecurityContext
5. Service layer enforces role-based and ownership-based access
6. Unauthorized access returns HTTP 403

---

## 🤖 AI Summary Flow

1. User creates a ticket via `POST /ticket/createTicket`
2. Service layer sends ticket details (issue, description, priority, status) to Gemini API
3. Gemini returns a 2-3 line intelligent summary
4. Summary is saved in `ai_summary` column in DB
5. Summary is returned in the ticket response

---

## ⚙️ Setup & Configuration

### Prerequisites
- Java 21
- MySQL
- Maven
- Google Gemini API key (free at [aistudio.google.com](https://aistudio.google.com))

### Environment Variable
Set this in your system environment variables:
```
GEMINI_API_KEY=your_gemini_api_key_here
```

### application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticket_managment
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
gemini.api.key=${GEMINI_API_KEY}
```

### Run
```bash
mvn spring-boot:run
```

---

## 📊 Current Status

- ✅ Authentication and Authorization implemented
- ✅ RBAC working
- ✅ Ownership-based access control
- ✅ DTO separation
- ✅ Transaction management
- ✅ Custom exception handling
- ✅ Pagination and sorting
- ✅ Filter by status and priority
- ✅ Full CRUD for tickets, users, comments
- ✅ AI-powered ticket summarization (Gemini API)
- ✅ Secure API key management via environment variables

🚧 Planned: deployment, Docker support, Swagger documentation, email notifications
