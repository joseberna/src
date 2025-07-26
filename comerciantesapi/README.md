
# 📦 Comerciantes API - Retos Fullstack Spring Boot

> **Autor:** Jose Fernando Berna  
> **Stack:** Java 17 • Spring Boot 3 • JPA/Hibernate • Oracle DB • Lombok • JWT • Docker

---

## 🚀 Descripción General

API RESTful para la gestión de comerciantes, establecimientos y reportes administrativos, desarrollada como evaluación para retos de un proceso Fullstack Java Spring Boot.  
Incorpora autenticación JWT, autorización por roles, paginación, documentación Swagger y exportación de reportes CSV.

---

## 🏆 Retos Implementados

| # | Descripción                                   | Estado  |
|---|-----------------------------------------------|---------|
| 1 | CRUD de Comerciantes                         | ✅      |
| 2 | CRUD de Establecimientos                     | ✅      |
| 3 | Login y Autenticación JWT                    | ✅      |
| 4 | Seguridad por Roles (Admin / Auxiliar)       | ✅      |
| 5 | Auditoría (Usuario y Fecha de Actualización) | ✅      |
| 6 | Paginación y Filtros Dinámicos               | ✅      |
| 7 | Documentación Swagger + Buenas Prácticas     | ✅      |
| 8 | Exportar Comerciantes a CSV (pipe `|`)       | ✅      |

---

## 🛠️ Stack y Herramientas

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **Oracle Database**
- **Lombok**
- **JUnit**
- **Swagger / OpenAPI**
- **Docker (build & run)**
- **Postman (colección incluida)**

---

## 📁 Estructura de Carpetas

```shell
src/
├── main/
│   ├── java/
│   │   └── com/retosbk/comerciantesapi/
│   │        ├── controller/
│   │        ├── service/
│   │        ├── service/impl/
│   │        ├── entity/
│   │        ├── repository/
│   │        ├── config/
│   │        └── util/
│   └── resources/
│       └── application.properties
└── test/
    └── java/...
```

---

## 📋 Endpoints Principales

| Método | Endpoint                           | Descripción                               | Seguridad           |
|--------|------------------------------------|-------------------------------------------|---------------------|
| POST   | /api/auth/login                    | Login y obtención de token JWT            | Pública             |
| GET    | /api/comerciantes                  | Listar comerciantes (paginado, filtro)    | JWT (Admin / Aux)   |
| POST   | /api/comerciantes                  | Crear comerciante                         | JWT (Admin / Aux)   |
| PUT    | /api/comerciantes/{id}             | Editar comerciante                        | JWT (Admin / Aux)   |
| DELETE | /api/comerciantes/{id}             | Inactivar comerciante (soft delete)       | JWT (solo Admin)    |
| PATCH  | /api/comerciantes/{id}/estado      | Cambiar estado de comerciante             | JWT (Admin / Aux)   |
| GET    | /api/comerciantes/reporte          | Descargar reporte CSV de comerciantes     | JWT (solo Admin)    |
| ...    | ...                                | ...                                       | ...                 |

---

## 🧑‍💻 Ejecución Local

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/tu_usuario/comerciantes-api.git
   cd comerciantes-api
   ```

2. **Configura la base de datos Oracle:**
   - Edita `src/main/resources/application.properties` con tus credenciales de Oracle DB.

3. **Ejecuta el proyecto:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Accede a la documentación Swagger:**
   - [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

---

## 🐳 Despliegue con Docker

### 1. **Build de la imagen**

Desde la raíz del proyecto (donde está el `Dockerfile`):

```bash
docker build -t comerciantes-api .
```

### 2. **Correr el contenedor**

Puedes crear un archivo `.env` con las variables necesarias (si las usas), o pasar las variables de entorno manualmente.

```bash
docker run -p 9090:9090 --env-file .env comerciantes-api
```

> El contenedor arrancará la API en el puerto **9090**.  
> **Recuerda**: El contenedor debe poder conectarse a tu base de datos Oracle.

---

## 📝 Reto 8: Reporte CSV Comerciantes

- **Endpoint:** `GET /api/comerciantes/reporte`
- **Auth:** Bearer Token (rol Administrador)
- **Formato:** CSV plano, separador `|`
- **Campos:**
  - Nombre | Municipio | Teléfono | Correo Electrónico | Fecha de Registro | Estado | Cantidad de Establecimientos | Total Ingresos | Cantidad de Empleados
- **Notas:**
  - Calcula dinámicamente los totales a partir de establecimientos asociados.
  - Solo devuelve comerciantes activos.
  - Descarga directa (`Content-Disposition: attachment`).

---

## 🧪 Pruebas Unitarias

Incluye pruebas básicas en `/src/test/java` para servicios y controladores principales.

```bash
./mvnw test
```

---

## 🔥 Buenas Prácticas Aplicadas

- Arquitectura limpia: separación por capas (Controller, Service, Repository, DTOs, Entities)
- Uso de Lombok para reducir boilerplate
- Validaciones `@Valid` en los endpoints
- Manejo de excepciones global
- Seguridad robusta con JWT y roles
- Respuestas HTTP estandarizadas (`ApiResponse`)
- Código y endpoints comentados
- Documentación Swagger para exploración fácil
- Colección Postman incluida para pruebas rápidas

---

## 📬 Contacto

¿Dudas o feedback?  
📧 josefbernam@gmail.com
