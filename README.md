# 🔐 Proyecto de Administración de Equipos - Spring Boot

Este es un proyecto backend desarrollado con **Spring Boot**, diseñado para administrar equipos con autenticación segura, gestión de usuarios y conexión a base de datos MySQL. Forma parte de un proyecto académico y sigue el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**.

---

## 🛠️ Tecnologías utilizadas

- ☕ [Spring Boot](https://spring.io/projects/spring-boot) – Framework para crear aplicaciones Java de forma rápida.
- 🔐 [Spring Security](https://spring.io/projects/spring-security) – Autenticación y autorización.
- 🐬 [MySQL](https://www.mysql.com/) – Base de datos relacional.
- 🌐 [Spring Web](https://spring.io/guides/gs/rest-service/) – Creación de servicios RESTful.
- 🧠 [JPA / Hibernate](https://spring.io/projects/spring-data-jpa) – Persistencia de datos.
- 💡 [Java](https://www.java.com/) – Lenguaje principal del proyecto.

---
## Integrantes

- **Figueroa Terán Lizzeth Susana** — 9°B  
- **Santibañez Cruz Lizbeth** — 9°B  
- **Hernandez Ruiz Alan Daniel** — 9°B  
- **Kevin David Rodríguez Zúñiga** — 9°B
- **Marmolejo Valle Alberto** — 9°B
- **Jared Adrián Juárez Bernal** — 9°B
## ⚙️ Requisitos previos

- Java 17 o superior
- Maven 3.8+
- MySQL Server
- IDE (IntelliJ, VS Code, Eclipse, etc.)

---

## 📥 Instalación y ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/MarmolejoValle/clocks-springboot/
cd clocks-springboot
2. Configura tu base de datos
Crea una base de datos en MySQL (por ejemplo: equipos_db).
El archivo application.properties contiene la configuración de conexión y no se incluye en el repositorio por razones de seguridad.
Debes crearlo manualmente dentro de la carpeta src/main/resources/.

# Archivo application.properties (privado)

spring.datasource.url=jdbc:mysql://localhost:3306/voteEnvent
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
⚠️ Este archivo no se incluye en el repositorio. Asegúrate de configurarlo correctamente.
3. Ejecuta la aplicación
Desde tu IDE o usando Maven:

mvn spring-boot:run
La aplicación estará disponible en:
http://localhost:8080

🧩 Patrón de arquitectura: MVC

El proyecto está estructurado siguiendo el patrón Modelo - Vista - Controlador (MVC):

model/: Clases entidad (representan la estructura de la base de datos).
repository/: Interfaces que acceden a la base de datos mediante JPA.
service/: Lógica de negocio y validaciones.
controller/: Manejo de peticiones HTTP (API REST).
🔐 Seguridad

El proyecto incluye seguridad mediante Spring Security. Se manejan roles y autenticación para restringir el acceso a ciertas rutas según el tipo de usuario (por ejemplo, administrador).

🔗 Endpoints API (ejemplo)

Método	Ruta	Descripción
POST	/auth/login	Iniciar sesión
POST	/auth/register	Registrar nuevo usuario

🤝 Contribuciones

Haz un fork del repositorio.
Crea una nueva rama: git checkout -b feature/nueva-funcionalidad
Realiza tus cambios y haz commit: git commit -m "Agrega nueva funcionalidad"
Haz push a tu rama: git push origin feature/nueva-funcionalidad
Abre un Pull Request 🚀
