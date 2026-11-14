# Secure Teacher Portal

Mini aplicación web individual que simula el portal de un maestro. Incluye frontend, backend con datos simulados, autenticación, validación de calificaciones, automatización con Selenium y análisis básico de seguridad con OWASP ZAP.

## Autor

**Víctor Raúl Torreblanca Franco**

Curso: Pruebas de Software

## Funcionalidades

- Inicio y cierre de sesión del maestro.
- Listado simulado de estudiantes y calificaciones.
- Actualización de calificaciones dentro del rango de 0 a 20.
- Registro de retroalimentación con validación de longitud.
- Protección CSRF, codificación de salida, BCrypt y control de acceso.
- Cabeceras CSP, Permissions Policy y aislamiento entre orígenes.

## Tecnologías

- Java 21 y Spring Boot 3.5.16
- Thymeleaf, HTML y CSS
- Spring Security y Jakarta Validation
- JUnit 5, MockMvc y Selenium 4.52
- OWASP ZAP 2.17 mediante Docker

## Ejecución

Requisitos: JDK 21 o superior y Maven 3.9.

```bash
mvn spring-boot:run
```

Abrir [http://localhost:8080/login](http://localhost:8080/login).

Credenciales de demostración:

```text
Usuario: teacher@school.edu
Contraseña: Secure123!
```

Si el puerto 8080 está ocupado por Jenkins:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=59086
```

## Pruebas

Pruebas unitarias, de integración y seguridad:

```bash
mvn test
```

Pruebas funcionales Selenium en Chrome:

```bash
mvn -Dtest=TeacherPortalSeleniumTest -Dsurefire.excludes= test
```

Resultados verificados:

- 7 pruebas unitarias/integración aprobadas.
- 2 pruebas Selenium aprobadas.
- 0 fallos.

## Análisis OWASP ZAP

1. Iniciar la aplicación en el puerto 59086.
2. Tener Docker Desktop activo.
3. Ejecutar:

```bash
./scripts/zap-baseline.sh
```

El primer análisis encontró cabeceras de seguridad ausentes. Después de la corrección, ZAP reportó **0 alertas altas, 0 medias y 0 bajas**. Los reportes completos están en [`docs/zap-reports`](docs/zap-reports).

## Documentación

- [Informe de seguridad](docs/informe-seguridad.md)
- [Comparación Selenium vs. Playwright](docs/comparacion-selenium-playwright.md)
- [Matriz de pruebas](docs/matriz-pruebas.md)
- [Guion para el video](docs/guion-video.md)
- [Evidencias](docs/evidencias)

## Ramas

- `main`: rama principal y estable.
- `develop`: desarrollo completo de la actividad.
