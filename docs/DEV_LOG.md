# Developer Log (DEV_LOG)

Este documento registra el proceso de construcción del E-commerce, enfocándose en la toma de decisiones técnicas y el aprendizaje de herramientas.

## Día 0: Definición de Arquitectura y Roadmap

### 1. ¿Por qué elegimos Next.js y Spring Boot por separado?
**Por qué:** Para cumplir con el estándar de la industria de "Separation of Concerns" (Separación de Preocupaciones).
**Para qué:** Permite que el Backend sea consumido por múltiples frentes (Web, App móvil, integración con terceros) y que el Frontend escale independientemente.
**Cómo:** El Backend expone una API REST y el Frontend la consume mediante HTTP.

### 2. ¿Por qué usamos la Arquitectura en Capas en Spring Boot?
**Por qué:** Para evitar que la lógica de negocio se mezcle con el acceso a datos o la presentación.
**Para qué:** Facilita el mantenimiento. Si cambiamos la lógica de cálculo de un descuento, solo tocamos la capa de `Service`, sin afectar la base de datos (`Repository`) o el controlador (`Controller`).
**Cómo:**
- `Controller`: Define los endpoints (la "cara" de la API).
- `Service`: Contiene la lógica (el "cerebro").
- `Repository`: Habla con la base de datos (las "manos").
- `Entity`: Representa la tabla (el "esqueleto").

### 3. ¿Qué herramientas iniciales estamos usando?
- **Maven**: Para gestionar las dependencias de Java (es como el `npm` de Java).
- **PostgreSQL**: Base de datos relacional robusta y profesional.
- **Spring Data JPA**: Para usar Java para hablar con la base de datos en lugar de escribir SQL puro manualmente.

## Día 1: Inicialización del Backend Profesional

### 1. El archivo `pom.xml` (Project Object Model)
**Por qué:** Un proyecto comercial necesita gestionar librerías externas (dependencias) de forma ordenada.
**Para qué:** El `pom.xml` le dice a Maven qué descargar (Spring Boot, Hibernate, Drivers de DB) y cómo compilar el proyecto.
**Cómo:** Definimos un `parent` (Spring Boot Starter Parent) que trae las versiones compatibles de todas las piezas para que no haya conflictos.

### 2. Dependencias elegidas y su propósito:
- **Spring Web**: Para crear los controladores REST.
- **Spring Data JPA**: El puente entre nuestros objetos Java y las tablas de PostgreSQL.
- **Lombok**: Una librería que genera automáticamente los Getters, Setters y Constructores. Esto mantiene el código limpio y profesional (Clean Code).
- **Validation**: Para asegurar que los datos que llegan del Frontend sean correctos antes de procesarlos.

### 3. Estructura de Carpetas (Layered Architecture)
Hemos creado una estructura de paquetes que sigue la **Arquitectura en Capas**:
- `config`: Configuraciones globales.
- `controllers`: La capa de entrada (API).
- `services`: La lógica de negocio pura.
- `repositories`: El acceso a datos.
- `entities`: El modelo de datos.
- `dtos`: Objetos para mover datos entre capas sin exponer las entidades.
- `exceptions`: Manejo profesional de errores.

## Día 2: Persistencia y Configuración

### 1. Configuración con YAML (application.yml)
**Por qué:** YAML es más legible y jerárquico que el formato tradicional de propiedades.
**Para qué:** Para centralizar credenciales de base de datos y comportamiento de Hibernate en un solo lugar.
**Cómo:**
- `spring.datasource`: Define la conexión física a PostgreSQL.
- `spring.jpa.hibernate.ddl-auto: update`: Esta es la clave del desarrollo ágil. Spring compara nuestras Entidades Java con la DB y crea las tablas si no existen.
- `show-sql: true`: Vital para el desarrollador; permite ver en consola qué está haciendo Spring bajo el capó.

### 2. Creación de la Base de Datos y Seguridad Inicial
**Por qué:** No debemos usar el usuario administrador de PostgreSQL (`postgres`) para la aplicación por seguridad.
**Para qué:** Para seguir el principio de "mínimo privilegio". La aplicación solo debe poder tocar su propia base de datos.
**Cómo:**
1.  Creamos la DB: `CREATE DATABASE ecommerce_db`.
2.  Creamos un usuario dedicado: `CREATE USER db_user WITH PASSWORD 'db_pass'`.
3.  Asignamos permisos: `GRANT ALL PRIVILEGES ON DATABASE ecommerce_db TO db_user`.

## Día 2 (Cont.): Primera Ejecución y Auditoría Base

### 1. ¡Éxito en el arranque!
**Resultado:** El servidor inició en el puerto 8080 y la conexión con PostgreSQL a través de HikariCP fue exitosa. 
**Aprendizaje:** El comando `fuser -k 8080/tcp` es vital en Linux para liberar puertos huérfanos antes de iniciar una app de Spring Boot.

### 2. Implementación de BaseEntity (Abstracción)
**Por qué:** En un sistema profesional, cada registro debe ser rastreable (cuándo se creó y cuándo cambió).
**Para qué:** Evitar la duplicación de código en todas las entidades del dominio.
**Cómo:**
- `@MappedSuperclass`: Permite que las propiedades de esta clase se hereden como columnas en las tablas de las clases hijas.
- `@PrePersist`: Un "hook" de JPA que ejecuta código justo antes de que el objeto se guarde en la DB por primera vez.
- `@PreUpdate`: Ejecuta código antes de cada actualización.

## Día 2 (Cont.): Módulo de Catálogo y Relaciones

### 1. Entidad Category (La primera tabla real)
**Por qué:** Es la base de la jerarquía de productos.
**Para qué:** Permite organizar los productos por tipos (ej: electrónica, ropa).
**Cómo:**
- `extends BaseEntity`: Aplicamos herencia para ganar automáticamente `id` y auditoría.
- `@Entity` + `@Table`: Le ordenan a JPA crear una tabla física en Postgres.
- **Resultado:** La tabla se creó con 5 columnas a pesar de que solo definimos 2 en la clase hija. ¡Abstracción pura!

### 💡 Tip de Supervivencia (PostgreSQL 15+)
**Problema:** `ERROR: permission denied for schema public`.
**Causa:** Las nuevas versiones de Postgres restringieron el permiso `CREATE` en el esquema público por defecto.
**Solución:** `GRANT ALL ON SCHEMA public TO db_user;` ejecutado como superusuario.

## Día 2 (Cont.): Relaciones y Tipos de Datos Financieros

### 1. Entidad Product (Relación ManyToOne)
**Por qué:** Un E-commerce no es nada sin productos relacionados a categorías.
**Para qué:** Demostrar cómo JPA maneja las llaves foráneas (Foreign Keys).
**Cómo:**
- `BigDecimal`: Usamos este tipo de dato para el precio para evitar errores de redondeo decimal (típico error de novato usar `double` en dinero).
- `@ManyToOne`: Indica que muchos productos pueden pertenecer a una misma categoría.
- `@JoinColumn`: Define el nombre de la columna física en la DB (`category_id`).

### 2. Capa de Repositorios (Spring Data JPA)
**Por qué:** Para no escribir SQL manual y usar el poder de los Genéricos.
**Para qué:** Centralizar todo el acceso a la base de datos de forma estándar.
**Cómo:**
- `JpaRepository<T, ID>`: Al extender de aquí, Spring implementa automáticamente los métodos CRUD básicos.
- `@Repository`: Marca la interfaz para que Spring la gestione como un Bean de persistencia.

## Día 2 (Cont.): Capa de Servicios (Lógica de Negocio)

### 1. El por qué de los Servicios
**Pregunta del USER:** "¿Por qué no usamos el Repositorio directo?"
**Respuesta:** Por desacoplamiento. El controlador no debe saber cómo se guardan los datos, solo debe invocar una acción de negocio. El Servicio es donde vive la "inteligencia" del E-commerce.
**Estructura:** Seguiremos el patrón `Interfaz` + `Implementación` para permitir flexibilidad futura (ej: cambiar la lógica sin afectar al controlador).
