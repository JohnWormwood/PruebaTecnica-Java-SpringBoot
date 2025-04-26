# ToDo API – Java + Spring Boot

Microservicio REST para gestionar **Tareas (To-Do)**, construido con Spring Boot. Ideal para tu portafolio y pruebas técnicas de selección.  

## 📋 Tabla de Contenidos

- [Descripción](#descripción)  
- [Características](#características)  
- [Tecnologías](#tecnologías)  
- [Requisitos Previos](#requisitos-previos)  
- [Instalación y Arranque](#instalación-y-arranque)  
- [Uso / Endpoints](#uso--endpoints)  
- [Documentación API](#documentación-api)  
- [Pruebas](#pruebas)  
- [CI / Badges](#ci--badges)  
- [Licencia](#licencia)  
- [Autor / Contacto](#autor--contacto)  

## 📖 Descripción

Esta API permite realizar un CRUD completo sobre una entidad **Tarea**, con validación, persistencia en H2 embebida y documentación automática Swagger/OpenAPI. Diseñada siguiendo buenas prácticas para una prueba técnica de Java/Spring Boot.  

## 🚀 Características

- CRUD de Tareas: crear, leer, actualizar, eliminar.  
- Validación de campos (`@NotBlank`, `@Valid`).  
- Base de datos H2 en memoria para desarrollo rápido.  
- Pruebas unitarias (JUnit 5 + Mockito) y de integración (SpringBootTest + MockMvc).  
- Documentación automática con Swagger UI.  

## 🛠 Tecnologías

- Java 17  
- Spring Boot 3.x  
- Spring Web, Spring Data JPA  
- H2 Database  
- JUnit 5, Mockito  
- Springdoc OpenAPI (Swagger UI)  
- Maven  

## ⚙️ Requisitos Previos

- JDK 17+ instalado.  
- Maven 3.x.  
- Git configurado (user.name, user.email).  

## 💾 Instalación y Arranque

1. Clona el repositorio:  
   ```bash
   https://github.com/JohnWormwood/PruebaTecnica-Java-SpringBoot.git
   cd /PruebaTecnica-Java-SpringBoot
   ```  
2. Construye y arranca la aplicación:  
   ```bash
   mvn clean spring-boot:run
   ```  
3. Accede a la API en `http://localhost:8080/api/tareas`.  

## 🔌 Uso / Endpoints

| Método | Ruta                 | Descripción             |
|--------|----------------------|-------------------------|
| GET    | `/api/tareas`        | Listar todas las tareas |
| GET    | `/api/tareas/{id}`   | Obtener tarea por ID    |
| POST   | `/api/tareas`        | Crear nueva tarea       |
| PUT    | `/api/tareas/{id}`   | Actualizar tarea        |
| DELETE | `/api/tareas/{id}`   | Eliminar tarea          |

Payload ejemplo (POST/PUT):
```json
{
  "titulo": "Comprar leche",
  "descripcion": "Ir al supermercado",
  "completada": false
}
```

## 📑 Documentación API

Swagger UI disponible en:  
```
http://localhost:8080/swagger-ui.html

```  
OpenAPI JSON en:  
```
http://localhost:8080/v3/api-docs

```

## ✅ Pruebas

- Ejecutar tests unitarios e integración:  
  ```bash
  mvn test
  ```
- Reportes generados en `target/surefire-reports`.  

## 🛡 CI / Badges

![Build Status](https://github.com/JohnWormwood/PruebaTecnica-Java-SpringBoot/blob/master/workflows/ci.yml/badge.svg)  

## 📜 Licencia

Este proyecto está bajo la [MIT License](LICENSE).  

## ✉️ Autor / Contacto

**Juan Ajenjo**  
- GitHub: https://github.com/JohnWormwood 
- Linkedin: www.linkedin.com/in/juan-ajenjo
