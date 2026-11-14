# Comparación: Selenium y Playwright

## Objetivo

Comparar ambas herramientas de automatización y justificar el uso de Selenium en esta actividad.

| Criterio | Selenium | Playwright |
|---|---|---|
| Enfoque | Estándar WebDriver para controlar navegadores como lo haría un usuario. | API moderna orientada a pruebas web de extremo a extremo. |
| Rendimiento | Puede tener mayor sobrecarga por la comunicación con WebDriver. | Generalmente rápido por su comunicación directa y contextos ligeros. |
| Esperas | Requiere administrar esperas explícitas en varios escenarios. | Incluye auto-waiting antes de acciones como `click` o `fill`. |
| Navegadores | Chrome, Firefox, Edge y Safari, con amplio soporte de fabricantes. | Chromium, Firefox y WebKit incluidos por el proyecto. |
| Lenguajes | Java, Python, JavaScript, C#, Ruby y otros bindings. | JavaScript/TypeScript, Python, Java y .NET. |
| Depuración | Logs, capturas y herramientas del ecosistema Selenium. | Trace Viewer, video, capturas y reporte HTML integrados. |
| Ecosistema | Muy maduro, gran comunidad, Selenium Grid y muchas integraciones. | Más reciente, configuración sencilla y excelente experiencia para proyectos modernos. |
| Curva de aprendizaje | Muy conocido, aunque exige entender drivers y sincronización. | API compacta y esperas automáticas que simplifican los primeros casos. |

## Ventajas principales

### Selenium

- Es un estándar ampliamente adoptado y compatible con múltiples lenguajes.
- Permite pruebas locales o distribuidas mediante Selenium Grid.
- Posee una comunidad madura y abundante documentación.
- Encaja naturalmente con este proyecto Java, Maven y JUnit 5.

### Playwright

- Sus verificaciones automáticas de disponibilidad reducen pruebas inestables.
- Incluye contextos aislados y herramientas visuales de diagnóstico.
- Facilita la ejecución paralela y la emulación de dispositivos.
- La instalación reúne los navegadores compatibles y evita parte de la configuración de drivers.

## Decisión

Se utilizó **Selenium** porque la actividad lo solicita expresamente y el proyecto está desarrollado en Java con JUnit 5. Playwright sería una excelente alternativa si se priorizaran tiempos de ejecución, trazas integradas y menor configuración de esperas.

## Fuentes consultadas

- [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/)
- [Navegadores compatibles con Selenium](https://www.selenium.dev/documentation/webdriver/browsers/)
- [Auto-waiting de Playwright](https://playwright.dev/docs/actionability)
- [Trace Viewer de Playwright](https://playwright.dev/docs/trace-viewer-intro)
