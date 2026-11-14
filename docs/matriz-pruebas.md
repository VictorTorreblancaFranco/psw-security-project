# Matriz de pruebas ejecutadas

| ID | Tipo | Escenario | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|---|
| PF-01 | Funcional Selenium | Iniciar sesión, seleccionar a Ana Torres, registrar nota 19 y comentario. | Se muestra confirmación y la tabla refleja nota 19. | Mensaje visible, nota y comentario actualizados. | Aprobado |
| PS-01 | Seguridad Selenium | Registrar un comentario que contiene un elemento `script`. | El contenido se muestra como texto y no ejecuta JavaScript. | No apareció el marcador JavaScript y el texto quedó escapado. | Aprobado |
| PS-02 | Integración | Enviar actualización de nota sin token CSRF. | El servidor rechaza la petición. | Respuesta HTTP 403. | Aprobado |
| PV-01 | Validación | Enviar una nota de 25. | El formulario rechaza valores superiores a 20. | Se mostró el mensaje de validación correspondiente. | Aprobado |
| PA-01 | Autorización | Acceder al panel sin autenticación. | Redirección hacia el inicio de sesión. | El servidor redirigió a `/login`. | Aprobado |
| PZ-01 | OWASP ZAP | Analizar pasivamente el portal antes de corregir cabeceras. | Identificar configuraciones inseguras. | 1 alerta media, 4 bajas y 3 informativas. | Hallazgos detectados |
| PZ-02 | OWASP ZAP | Repetir el análisis después de la remediación. | Eliminar alertas medias y bajas. | 0 altas, 0 medias, 0 bajas y 3 informativas. | Aprobado |

## Resumen

- Pruebas Java ejecutadas: 7 aprobadas.
- Pruebas Selenium ejecutadas: 2 aprobadas.
- Fallos funcionales: 0.
- Alertas de seguridad medias o altas después de la corrección: 0.
