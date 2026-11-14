# Guion para el video con cámara activa

Duración sugerida: 5 a 7 minutos.

## 1. Presentación y objetivo

> Hola, soy Víctor Raúl Torreblanca Franco. En esta actividad desarrollé Secure Teacher Portal, una aplicación web que simula el portal de un maestro. El objetivo fue automatizar una prueba funcional con Selenium y evaluar configuraciones básicas de seguridad con OWASP ZAP.

Mostrar el `README.md` y la estructura del proyecto.

## 2. Construcción del proyecto

> La aplicación utiliza Java, Spring Boot, Thymeleaf y Spring Security. El backend mantiene tres estudiantes simulados en memoria. El maestro inicia sesión y puede actualizar una calificación y su retroalimentación. El servidor valida que la nota esté entre cero y veinte.

Mostrar:

- `StudentService.java`, donde están los datos simulados.
- `PortalController.java`, donde se procesa el formulario.
- `SecurityConfig.java`, donde están la autenticación y las cabeceras.

Iniciar la aplicación y mostrar el login. Utilizar:

```text
teacher@school.edu
Secure123!
```

## 3. Automatización con Selenium

> La clase TeacherPortalSeleniumTest abre Chrome, completa el inicio de sesión, selecciona un estudiante, registra una nota y comprueba el mensaje de éxito y el valor actualizado. Una segunda prueba envía un texto con apariencia de script y verifica que se muestre como texto sin ejecutar código.

Mostrar `TeacherPortalSeleniumTest.java` y ejecutar:

```bash
mvn -Dtest=TeacherPortalSeleniumTest -Dsurefire.excludes= test
```

Mostrar al final `Tests run: 2, Failures: 0` y las capturas de `docs/evidencias`.

## 4. Selenium frente a Playwright

> Selenium destaca por su madurez, soporte de lenguajes y compatibilidad con navegadores y Selenium Grid. Playwright suele ofrecer una configuración más sencilla, auto-waiting y herramientas integradas como Trace Viewer. Elegí Selenium porque la actividad lo solicita y se integra directamente con Java y JUnit 5.

Mostrar la tabla de `docs/comparacion-selenium-playwright.md`.

## 5. Análisis OWASP ZAP

> Ejecuté un baseline scan de OWASP ZAP mediante Docker. El análisis inicial encontró una alerta media por ausencia de Content Security Policy, cuatro alertas bajas relacionadas con aislamiento de orígenes y Permissions Policy, y tres avisos informativos.

Mostrar `zap-before.png`.

> Como corrección añadí CSP, Permissions Policy y las cabeceras COEP, COOP y CORP. Al repetir el análisis obtuve cero alertas altas, medias y bajas. Solamente quedaron tres avisos informativos esperados por el uso de autenticación, sesión y control de caché.

Mostrar `SecurityConfig.java` y después `zap-after.png`.

## 6. Cuatro conclusiones

> Primera conclusión: las pruebas funcionales automatizadas permiten repetir un flujo importante y detectar rápidamente si un cambio rompe el comportamiento esperado.

> Segunda conclusión: las validaciones deben aplicarse en el backend, porque los controles del navegador pueden ser modificados o evitados por un atacante.

> Tercera conclusión: las herramientas de seguridad como OWASP ZAP descubren configuraciones que no siempre son visibles durante una prueba funcional normal.

> Cuarta conclusión: la seguridad no es una revisión aislada al final del proyecto; debe verificarse continuamente junto con las pruebas de regresión y las actualizaciones de dependencias.

## 7. Cierre

> En conclusión, el portal quedó funcionando, las nueve pruebas automatizadas aprobaron y el segundo análisis de seguridad no presentó alertas altas, medias ni bajas. Gracias.
