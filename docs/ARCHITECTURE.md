# Arquitectura del Proyecto

Este documento describe la estructura técnica y el flujo de datos del E-commerce.

## Diagrama de Clases (Módulo de Catálogo)

```mermaid
classDiagram
    class BaseEntity {
        <<abstract>>
        +Long id
        +LocalDateTime createdAt
        +LocalDateTime updatedAt
        #prePersist()
        #preUpdate()
    }

    class Category {
        +String name
        +String description
    }

    class Product {
        +String name
        +String description
        +BigDecimal price
        +Integer stock
        +Category category
    }

    Category --|> BaseEntity
    Product --|> BaseEntity
    Product "*" --> "1" Category : belongs to

    class CategoryRepository {
        <<interface>>
    }

    class ProductRepository {
        <<interface>>
    }

    class ICategoryService {
        <<interface>>
        +findAll() List~Category~
        +findById(Long id) Category
        +save(Category c) Category
        +delete(Long id) void
    }

    class IProductService {
        <<interface>>
        +findAll() List~Product~
        +findById(Long id) Product
        +save(Product p) Product
        +delete(Long id) void
    }

    class CategoryServiceImpl {
        -CategoryRepository repo
    }

    class ProductServiceImpl {
        -ProductRepository repo
    }

    class CategoryController {
        -ICategoryService service
        +getAll() List~Category~
    }

    CategoryServiceImpl ..|> ICategoryService : implements
    CategoryServiceImpl --> CategoryRepository : uses
    ProductServiceImpl ..|> IProductService : implements
    ProductServiceImpl --> ProductRepository : uses
    CategoryController --> ICategoryService : uses
    CategoryRepository ..> Category : manages
    ProductRepository ..> Product : manages
```

## Flujo de una Petición (Request Flow)

1.  **Client** (Browser/Postman) -> HTTP GET `/api/categories`
2.  **CategoryController** -> recibe la petición y llama al Service.
3.  **CategoryServiceImpl** -> ejecuta lógica y llama al Repository.
4.  **CategoryRepository** -> ejecuta consulta SQL via Hibernate.
5.  **PostgreSQL** -> devuelve los datos.
6.  **Results** -> viajan de vuelta por todas las capas hasta el cliente en formato JSON.
