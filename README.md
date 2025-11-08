# 🧮 Catálogo e Inventario – CRM Mesa Regia

Módulo de **gestión de catálogo e inventario** desarrollado en **Java 21** utilizando **Spring Boot 3.5.7**.  
Forma parte del sistema CRM *Mesa Regia*, orientado al control de artículos, tipos, categorías y colores, con servicios RESTful documentados mediante **SpringDoc OpenAPI**.

---

## 🚀 Características principales

- **Lenguaje:** Java 21  
- **Framework:** Spring Boot 3.5.7  
- **Gestor de dependencias:** Apache Maven  
- **Base de datos:** H2 (modo embebido, para pruebas)  
- **ORM:** Spring Data JPA con Hibernate  
- **Documentación API:** SpringDoc OpenAPI + Swagger UI  
- **Estilo de arquitectura:** REST + HATEOAS  
- **Anotaciones y productividad:** Project Lombok  
- **Validación:** Jakarta Validation  

---

## 🧰 Librerías y dependencias clave

| Librería | Descripción | Versión |
|-----------|--------------|----------|
| `spring-boot-starter-web` | Controladores REST y manejo HTTP | 3.5.7 |
| `spring-boot-starter-data-jpa` | Persistencia de datos con JPA/Hibernate | 3.5.7 |
| `spring-boot-starter-hateoas` | Enlaces hipermedia en las respuestas REST | 3.5.7 |
| `springdoc-openapi-starter-webmvc-ui` | Generación de documentación Swagger UI | 2.x |
| `lombok` | Reducción de código repetitivo mediante anotaciones | Última estable |
| `jakarta.validation` | Validaciones de entidades | Última estable |
| `h2` | Base de datos embebida para pruebas | 2.3.232 |

---

## ⚙️ Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 21 JDK**
- **Maven 3.9+**
- **Git**
- IDE recomendado: *Spring Tool Suite (STS)* o *IntelliJ IDEA*

---

## ▶️ Ejecución del proyecto

1. **Clonar el repositorio**
   git clone https://github.com/cgocgit/catalogoinventario.git

2. **Compilar el codigo**
	mvn clean install

3. **Ejecutar aplicación**
	mvn spring-boot:run
	
4. **Acceder a la aplicación**
	Documentación Swagger UI: http://localhost:8080/swagger-ui.html
	
---

## 📘 Documentación de la API

El proyecto utiliza SpringDoc OpenAPI para la generación automática de la especificación y la interfaz Swagger:

- Swagger UI:
http://localhost:8080/swagger-ui.html

- OpenAPI JSON:
http://localhost:8080/v3/api-docs

---

## 💡 Créditos y autoría

Proyecto académico desarrollado por:

Carlos Gilberto Olvera Casanova.

📚 IUV Universidad Virtual – Desarrollo de Software

🗓️ Año: 2025

---

## 🔗 Referencias técnicas

- Spring Boot Documentation

- Spring Data JPA

- Spring HATEOAS

- SpringDoc OpenAPI

- Maven Project Documentation

- Java SE 21 Documentation