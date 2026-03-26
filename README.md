# 🤖 Reach CRM - Backend API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring_boot-%236DB33F.svg?style=flat-square&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=Spring-Security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=flat-square&logo=JSON%20web%20tokens)
![PostgreSQL](https://img.shields.io/badge/postgresql-%23316192.svg?style=flat-square&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=flat-square&logo=docker&logoColor=white)

Una API RESTful de grado corporativo desarrollada en **Java 21** y **Spring Boot 3**, diseñada para gestionar clientes y un pipeline de ventas (Kanban) con estricta integridad relacional y seguridad Stateless.

## 🛠️ Stack Tecnológico
* **Core:** Java 21, Spring Boot 3.x
* **Base de Datos:** PostgreSQL 15 (vía Docker/Testcontainers)
* **ORM & Data:** Spring Data JPA, Hibernate
* **Seguridad:** Spring Security, JSON Web Tokens (jjwt)
* **Validación & Build:** Hibernate Validator, Maven

## 🏗️ Arquitectura y Patrones de Diseño
Este proyecto sigue los principios de Clean Code y Arquitectura en Capas (Controller -> Service -> Repository), implementando los siguientes patrones:

* **DTO Pattern (Data Transfer Object):** Implementado mediante `Records` inmutables de Java 14+ para evitar sobreexposición de entidades JPA y optimizar el payload HTTP.
* **Global Exception Handling:** Uso de `@RestControllerAdvice` para interceptar excepciones de negocio (ej. `DataIntegrityViolationException`) y devolver respuestas HTTP estandarizadas.
* **Inyección de Dependencias por Constructor:** Promoviendo la inmutabilidad y facilitando el testing, evitando el uso de `@Autowired` en propiedades.
* **Seguridad Stateless (JWT):** Implementación de un `OncePerRequestFilter` personalizado para validar firmas criptográficas en cada petición, sin almacenar sesiones en el servidor.

## 🔒 Endpoints Principales
* `POST /api/auth/login` - Autenticación y generación de JWT.
* `GET /api/clientes` - Lista de clientes protegida por JWT.
* `PUT /api/oportunidades/{id}/etapa` - Actualización de estado del Kanban.

## 🚀 Cómo ejecutar en local
1. Levantar la base de datos PostgreSQL usando Docker Compose: `docker-compose up -d`
2. Compilar el proyecto: `mvn clean install`
3. Ejecutar la aplicación: `mvn spring-boot:run`