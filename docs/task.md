# E-commerce Roadmap Checklist

## Phase 1: Foundation & Base Architecture [/]
- [x] Establish Documentation Standards [x]
  - [x] Create `DEV_LOG.md` (Learning log)
  - [x] Create `AI_CONTEXT.md` (AI Sync log)
- [ ] Initialize Backend Project (Spring Boot) [/]
  - [x] Set up `pom.xml` with modular dependencies
  - [ ] Configure `application.yml` for PostgreSQL
  - [ ] Create Global Exception Handling infrastructure (Abstraction)
  - [ ] Define Base Entity (Auditing fields: createdAt, updatedAt)
- [ ] Initialize Frontend Project (Next.js 15+)
  - [ ] Set up project with Tailwind CSS
  - [ ] Define shared components library (Atomic Design)
  - [ ] Implement API client abstraction (Axios/Fetch wrapper)

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
