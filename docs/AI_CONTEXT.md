# AI Context (AI_CONTEXT)

**Current Status:** Planning Phase - Foundation documentation.
**Last Update:** 2026-01-19

## Technical Stack Summary
- **Backend:** Java 17+, Spring Boot 3.x, Maven, PostgreSQL, Spring Data JPA, Spring Security (JWT - deferred).
- **Frontend:** Next.js 15+ (App Router), Tailwind CSS, TypeScript.

## Project Structure (Target)
- Layered Architecture (Controller-Service-Repository-Entity).
- DTOs for data transfer.
- Abstraction-first approach.

## Current Progress
- [x] Initial UML and ERM designed.
- [x] Implementation Plan created and refined with user feedback.
- [x] Project Roadmap (task.md) established.
- [x] Foundational logs (DEV_LOG, AI_CONTEXT) initialized.
- [x] Backend directory structure created.
- [x] Initial `pom.xml` configured.
- [x] Successful Backend startup with PostgreSQL connectivity.
- [x] Catalog Module infrastructure (Category/Product Entities, Repositories, Services) complete.

## Next Steps for the AI
1.  **Expose API Endpoints**: Create REST Controllers for Category and Product.
2.  **Global Exception Handling**: Implement the @RestControllerAdvice infrastructure.
3.  **Data Transfer Objects (DTOs)**: Introduce DTOs for cleaner API communication (Abstraction).
