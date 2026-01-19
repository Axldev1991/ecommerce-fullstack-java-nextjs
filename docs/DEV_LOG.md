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
