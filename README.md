# Examen Práctico Fullstack: Sistema CRUD de Dependencias de Oaxaca

## Descripción
Este proyecto es un sistema **CRUD** para administrar dependencias de Oaxaca, que incluye **backend en Java con Spring Boot + PostgreSQL** y **frontend en Angular**.  
Permite crear, leer, actualizar y eliminar dependencias, así como listar con paginación y buscar por nombre.

---

## Tecnologías

### Backend
- Lenguaje: Java
- Framework: Spring Boot
- Base de datos: PostgreSQL
- Dependencias: Spring Data JPA, Spring Web, Validation

### Frontend
- Framework: Angular
- Librerías: Angular Material (opcional para tablas y formularios)
- HTML / CSS / TypeScript

---

## Funcionalidades

### Backend (Spring Boot)
- Endpoints CRUD:
  - `POST /dependencias` → Crear dependencia
  - `GET /dependencias` → Listar todas las dependencias
  - `GET /dependencias/{id}` → Obtener dependencia por ID
  - `PUT /dependencias/{id}` → Actualizar dependencia
  - `DELETE /dependencias/{id}` → Eliminar dependencia
- Listar dependencias con paginación
- Filtrar dependencias por nombre
- Validaciones:
  - `nombre`: obligatorio
  - `telefono`: opcional, máximo 20 caracteres

### Frontend (Angular)
- Tabla con todas las dependencias
- Formulario para agregar y editar dependencias
- Búsqueda por nombre
- Paginación en la tabla
