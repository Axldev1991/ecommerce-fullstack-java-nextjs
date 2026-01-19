# E-commerce Architectural Planning

This document outlines the strategy for building a modular, scalable, and decoupled E-commerce system using Spring Boot (Backend) and Next.js (Frontend). 

> [!IMPORTANT]
> While this project has a strong didactic component, the ultimate goal is to deliver a **Commercial-Grade MVP** that meets the highest modern standards of the industry, making it a viable product for real-world clients.

## Core Principles

*   **Logic over Code**: The focus is on understanding the "Why" and "How" of Spring Boot patterns so the developer can implement the "What".
*   **Decoupling (Abstraction)**: Layers (Controller, Service, Repository) will interact through interfaces and DTOs.
*   **Modular Growth**: Features are developed as independent modules (Catalog, Order, Admin) to allow easy replacement or extension.
*   **Future-Proof Security**: The system will be built to allow a seamless JWT integration later by keeping Auth logic separate from Business logic.
*   **Exhaustive Documentation**: Every tool, library, and pattern used will be documented explaining the **Why**, **What for**, and **How** it's applied.

---

## Phase 1: Foundation (The Skeleton)

### Backend: Spring Boot Infrastructure
We will start by defining the global architecture that will support all future modules.

1.  **Auditing Base Class**: Instead of repeating `createdAt` and `updatedAt` in every table, we'll create a `BaseEntity` (Abstract class) that all entities will inherit from.
2.  **Global Exception Handling**: We will implement a `@RestControllerAdvice`. This is a central "checkpoint" that catches any error thrown in the system and transforms it into a clean, understandable JSON for the Frontend.
3.  **DTO Strategy**: No Entity will ever leave the Backend. We will define strict `Request` and `Response` DTOs.

### Frontend: Next.js Architecture
1.  **API Service Layer**: We'll create a central API handler (using native `fetch`) that manages headers and error states in one place.
2.  **Theme Strategy**: Set up CSS variables for colors and typography to make the UI "skinnable" and professional.

---

## Phase 2: Catalog Module (Business Logic Implementation)

This will be our first "Real" module.

1.  **Entity-Relationship**: Implementing the Category -> Product 1:N relationship using JPA annotations.
2.  **Persistence Layer**: Creating `JpaRepository` interfaces.
3.  **Service Layer Abstraction**: Defining a `ProductService` interface. This follows the **Dependency Inversion Principle (D from SOLID)**: the Controller depends on an interface, not a concrete implementation.
4.  **Frontend Server Components**: Leveraging Next.js 15 capabilities to fetch product data directly on the server for SEO.

---

## Phase 3: Order & Checkout Logic

1.  **Transactional Integrity**: Using `@Transactional` to ensure that if an order item fails to save, the entire order is rolled back (Atomic operations).
2.  **State Management**: Implementing a client-side cart that validates against backend stock before final checkout.

---

## Development Workflow for each Step

> [!IMPORTANT]
> For every feature, he will follow this cycle:
> 1. **Conceptual Design**: We discuss the UML and the logic (Entities, Relationships).
> 2. **Structural Setup**: We define the DTOs and Interfaces.
> 3. **Implementation**: You write the code based on the logic we defined.
> 4. **Verification**: We test the endpoint or the UI component.

---

## Verification Plan

### Backend
- **Unit Testing**: Testing the `Service` layer logic (e.g., price calculation) independently from the database.
- **Integration Testing**: Using tool `run_sql` to verify that data is correctly stored in PostgreSQL.

### Frontend
- **Component isolation**: Verifying that UI components behave correctly with mock data.
- **E2E Flow**: Testing the path from "View Product" to "Add to Cart".
