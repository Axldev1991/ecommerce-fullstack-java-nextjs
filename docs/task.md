# E-commerce Roadmap Checklist

## Phase 1: Foundation & Base Architecture [/]
- [x] Establish Documentation Standards [x]
  - [x] Create `DEV_LOG.md` (Learning log)
  - [x] Create `AI_CONTEXT.md` (AI Sync log)
- [x] Initialize Backend Project (Spring Boot) [x]
  - [x] Set up `pom.xml` with modular dependencies
  - [x] Configure `application.yml` for PostgreSQL
  - [x] Create Global Exception Handling infrastructure (Abstraction) [x]
  - [x] Define Base Entity (Auditing fields: createdAt, updatedAt) [x]
- [ ] Initialize Frontend Project (Next.js 15+)
  - [ ] Set up project with Tailwind CSS
  - [ ] Define shared components library (Atomic Design)
  - [ ] Implement API client abstraction (Axios/Fetch wrapper)
- [x] Catalog Module (Phase 2)
  - [x] Implement Category entity
  - [x] Implement Product entity (ManyToOne)
  - [x] Create Repositories (JpaRepository)
  - [x] Create Catalog Services (Interfaces & Impl)
  - [x] Create REST Controllers (API Endpoints)
  - [x] Verify End-to-End data flow (Category creation successful)
- [x] Catalog refinement [x]
  - [x] Implement Global Exception Handling [x]
  - [x] Implement DTOs for Products and Categories [x]

## Phase 2: Catalog Module (The Storefront) [ ]
- [ ] Backend: Category & Product Entities
- [ ] Backend: Data Transfer Objects (DTOs) for Products
- [ ] Backend: Service layer for Category/Product business logic
- [ ] Backend: REST API for Catalog (Read-only for now)
- [ ] Frontend: Product Grid View (Server Components)
- [ ] Frontend: Category Filters & Search logic

## Phase 3: Interactive Cart & User State [ ]
- [ ] Frontend: Shopping Cart storage logic (Client Components)
- [ ] Frontend: Cart context/store (Zustand/React Context)
- [ ] Backend: Stock validation endpoints
- [ ] Frontend: UI for adding/removing items with feedback

## Phase 4: Order Processing & Checkout [ ]
- [ ] Backend: Order & OrderItem Entities (Relationship handling)
- [ ] Backend: Transactional Service for Order creation
- [ ] Frontend: Checkout summary and shipping info form
- [ ] Backend: DTO mapping for complex Order objects

## Phase 5: Administration (Backoffice) [ ]
- [ ] Frontend: Admin Dashboard layout
- [ ] Frontend: Forms for Product CRUD
- [ ] Backend: Administrative endpoints (Full CRUD)
- [ ] Frontend: Visualizing Sales/Orders status

## Phase 6: Payment Gateway Integration [ ]
- [ ] Backend: Integration with Mercado Pago API (Service abstraction)
- [ ] Frontend: Payment redirect and Webhook handling logic
- [ ] Backend: Updating Order status via Webhooks (Safety first)

## Phase 7: Security Hardening (JWT) [ ]
- [ ] Backend: Spring Security configuration
- [ ] Backend: User authentication flow (Login/Register)
- [ ] Backend: JWT implementation (Filters & Providers)
- [ ] Frontend: Middleware for protected routes (Admin/User Profile)
- [ ] Frontend: Auth token persistence in Cookies/Storage
