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

## Next Steps for the AI
1.  **Application Main Class**: Create the entry point for Spring Boot.
2.  **Configuration**: Set up `application.yml` for database connectivity.
3.  **Base Classes**: Define `BaseEntity` and `GlobalExceptionHandler`.
