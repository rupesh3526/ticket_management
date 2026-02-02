# 🎫 Ticket Management System (Backend)

A backend-focused Ticket Management System built using **Spring Boot and JPA**.  
This repository currently contains the **foundation of the system** and is being built step by step.

The project is still in progress and not feature-complete.

---

## 🎯 Project Intent

The goal of this project is to build a **realistic internal IT support system**, similar to what companies use to manage issues and requests.

The focus is on:
- Clear role separation  
- Controlled access to data and actions  
- Being able to trace who did what and when  

For now, the project is being kept small so the core design can settle before adding more features.

---

## 🧱 Current Status (Implemented)

### ✅ Domain Setup
- User entity implemented
- Role defined (`USER`, `AGENT`, `ADMIN`)
- JPA / Hibernate mappings configured

### ✅ Persistence
- User repository implemented
- Database connection tested and working

### ✅ Basic Verification
- User creation flow tested and working
- Database CRUD operations verified
- Ticket creation and update functionality enhanced and validated




---

## 🚫 Out of Scope (For Now)

The following items are **not implemented yet** and will stay out of scope until the foundation is complete:


- Authentication and authorization
- Role-based access control
- Audit logging
- Security (JWT, Spring Security)
- Validation and global exception handling
- Pagination, filtering, or search
- Deployment and infrastructure setup

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- Hibernate / JPA  
- MySQL  
- Maven  

---

## 📁 Current Project Structure
```text
ticket-management
|
|-- Entity
|-- EntityDTO
|-- Exception
|-- controller
|-- repository
|-- security
|-- service
|-- TicketManagementApplication.java
```
This structure will change only when new features are actually added.

---

## 🧭 Development Roadmap

### Phase 1 – Foundation (Current)
- Core domain entities
- Database schema
- Basic persistence
- Sanity checks

No additional features will be added in this phase.

---

### Phase 2 – Ticket Core
- Ticket entity
- Ticket status lifecycle
- Ownership and assignment rules

---

### Phase 3 – Access Control
- Authentication
- Role-based authorization
- Endpoint protection

---

### Phase 4 – Audit & Traceability
- Action logging
- Change history
- Admin visibility

---

### Phase 5 – Hardening & Deployment
- Validation
- Security improvements
- Dockerization and deployment

---

## 📌 Development Notes

- Features are added only after the current phase is complete
- Scope creep is avoided on purpose
- The README is updated only when the code changes

---

## 📝 Status

🚧 **Work in progress — foundation phase**

This README reflects the current state of the codebase.
