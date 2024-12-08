# users API

API desarrollada en Java 17 para gestionar perfiles de pagos, notificaciones y operaciones relacionadas, siguiendo una arquitectura hexagonal.

## Tecnologías
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**


## Requisitos
- Tener **Java 17** instalado en tu máquina. Puedes descargar Java Aduptium [aquí](https://adoptium.net/es/temurin/releases/?version=17).
- **Maven** (versión 3.8.4 o superior). Descárgalo desde [Maven](https://maven.apache.org/download.cgi).

## Cómo Iniciar el Proyecto

### Opcion 1: Usando Docker para la base de datos

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/Noctis-Dev/users-hexagonal.git
   cd users-hexagonal
   ```

2. **El archivo `application.properties` ya biene configuardo por lo que no hay que mover nada:**
   

3. **Compila el proyecto:**
   ```bash
   mvn clean install
   ```

4. **Ejecuta la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

5. **Accede a la API:**
   Abre [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) para ver la documentación interactiva de la API.

### Notas Finales
- Si encuentras algún problema, por favor abre un _issue_ en el repositorio.
