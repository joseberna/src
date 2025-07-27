# 📦 Prueba Integral Lite (PLSQL-Java-React)

**Autor:** Jose Fernando Berna  
**Stack:** PL/SQL · Java 17 · Spring Boot 3 · JPA/Hibernate · Oracle DB · Lombok · JWT · React 18 · Docker

---

## 🚀 Descripción General

Solución integral para la gestión de comerciantes, establecimientos y reportes administrativos, desarrollada como evaluación técnica para retos Fullstack (PLSQL · Java · React).  
Incluye autenticación JWT, autorización por roles, paginación, exportación de reportes CSV, pruebas unitarias y despliegue con Docker.

---

## 🏆 Retos Cumplidos

### PL/SQL

| #   | Descripción                                                        | Estado |
|-----|--------------------------------------------------------------------|--------|
| 1   | Modelo de datos normalizado: Usuarios, Comerciantes, Establecimientos, campos de auditoría | ✅     |
| 2   | Secuencias, triggers para PK y actualización automática de auditoría | ✅     |
| 3   | Datos semilla para cada entidad (usuarios, comerciantes, establecimientos) | ✅     |
| 4   | Función/cursor referenciado con reporte completo de comerciantes activos | ✅     |

---

### Java · Spring Boot

| #   | Descripción                                                        | Estado |
|-----|--------------------------------------------------------------------|--------|
| 5   | Autenticación JWT, login y seguridad por roles (Administrador, Auxiliar) | ✅     |
| 6   | Endpoint lista de municipios (con opción de cache), respuestas estandarizadas | ✅     |
| 7   | CRUD completo de Comerciantes (paginado, validaciones, roles, filtros) | ✅     |
| 8   | Endpoint exportar comerciantes activos a CSV (pipe-separated) usando función de PLSQL | ✅     |
|     | Pruebas unitarias JUnit para el CRUD de Comerciantes | ✅     |
|     | Documentación Swagger y Dockerfile para despliegue | ✅     |

---

### React Frontend

| #   | Descripción                                                        | Estado |
|-----|--------------------------------------------------------------------|--------|
| 9   | Pantalla Login: usuario, contraseña, checkbox términos, consume login backend | ✅     |
|     | Header con nombre y rol del usuario autenticado                    | ✅     |
| 10  | Página Home: tabla de comerciantes con paginado, descarga CSV, crear nuevo | ⏳     |
| 11  | Página Formulario: creación/edición de comerciante, validadores, selector municipio, sumatoria de ingresos/empleados (footer, sólo editar) | ⏳     |
|     | Pruebas unitarias con Jest para componentes principales            | ⏳     |

> ⏳ Los retos 10 y 11 del frontend no pudieron completarse completamente por falta de tiempo, pero la estructura está implementada y documentada para continuar. 

---

## 🛠️ Stack y Herramientas

- **PL/SQL** (Oracle 11gR2+)
- **Java 17 / Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA / Hibernate**
- **Lombok**
- **JUnit 5**
- **Swagger / OpenAPI**
- **Docker**
- **React 18** (con gestión de estado, seguridad básica y buenas prácticas)
- **Jest** (test unitarios frontend)
- **Postman** (colección incluida)

---

## 📑 Estructura de Carpetas

```shell
backend/
├── src/
│   ├── main/java/com/retosbk/comerciantesapi/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── service/impl/
│   │   ├── entity/
│   │   ├── repository/
│   │   ├── config/
│   │   └── util/
│   └── resources/
│       └── application.properties
│
└── test/java/...
frontend/
├── src/
│   ├── components/
│   ├── pages/
│   ├── styles/
│   └── ...
└── public/
```

📋 Endpoints Principales
```shell
Método	Endpoint	Descripción	Seguridad
POST	/api/auth/login	Login y obtención de token JWT	Pública
GET	/api/comerciantes	Listar comerciantes (paginado, filtro)	JWT (Admin / Aux)
POST	/api/comerciantes	Crear comerciante	JWT (Admin / Aux)
PUT	/api/comerciantes/{id}	Editar comerciante	JWT (Admin / Aux)
DELETE	/api/comerciantes/{id}	Inactivar comerciante (soft delete)	JWT (solo Admin)
PATCH	/api/comerciantes/{id}/estado	Cambiar estado de comerciante	JWT (Admin / Aux)
GET	/api/comerciantes/reporte	Descargar reporte CSV de comerciantes	JWT (solo Admin)
...	...	...	...
```

🧑‍💻 Ejecución Local Backend
Clona el repositorio:

```shell
git clone https://github.com/tu_usuario/comerciantes-api.git
cd comerciantes-api/backend
```

Configura la base de datos Oracle:

Edita src/main/resources/application.properties con tus credenciales de Oracle DB.

Ejecuta el proyecto:

```shell
./mvnw spring-boot:run
```
Accede a la documentación Swagger:

http://localhost:9090/swagger-ui.html

🐳 Despliegue con Docker
Build de la imagen
bash
Copy
Edit
docker build -t comerciantes-api .
Correr el contenedor
bash
Copy
Edit
docker run -p 9090:9090 --env-file .env comerciantes-api
El contenedor debe poder conectarse a tu base de datos Oracle.

🧪 Pruebas Unitarias Backend
Incluye pruebas básicas en /src/test/java para servicios y controladores principales.
Para ejecutarlas:

```shell
./mvnw test
```
🧑‍💻 Ejecución Local Frontend
Clona el frontend

```shell
cd ../frontend
npm install
```
Configura el endpoint backend

Edita el archivo .env o la configuración donde esté definida la URL del backend (por defecto http://localhost:9090).

Ejecuta la app

```shell
npm start
```
✅ Pruebas Unitarias Frontend
Incluye pruebas en Jest para componentes principales (login y home).

```shell
npm test
```
🔥 Buenas Prácticas Aplicadas
Arquitectura limpia: separación por capas (Controller, Service, Repository, DTOs, Entities)

Uso de Lombok, validaciones @Valid, manejo de excepciones global, respuestas estandarizadas

Seguridad JWT, roles, CORS

Código comentado, documentación Swagger, colección Postman

Test unitarios en backend y frontend

Frontend moderno, validadores y feedback UI, OWASP Top 10

📝 Retos Pendientes
Por falta de tiempo, los siguientes retos quedaron parcialmente implementados o pendientes de ajuste final:

Reto 10: Tabla Home con paginación, acciones y diseño final.

Reto 11: Formulario de creación/edición de comerciantes con sumatorias dinámicas de ingresos y empleados.

Test unitarios adicionales en frontend.

📬 Contacto
¿Dudas o feedback?
📧 josefbernam@gmail.com