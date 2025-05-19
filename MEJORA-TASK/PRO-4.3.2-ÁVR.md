## Preguntas


[1]
### 1.a ¿Qué code smell y patrones de refactorización has aplicado?
He aplicado los siguientes code smells y patrones de refactorización:  
Demasiada anidación (NestedBlockDepth): Refactorizado extrayendo lógica a funciones auxiliares.
Clase con demasiadas funciones (TooManyFunctions): Refactorizado dividiendo la clase en servicios más pequeños.
Demasiados parámetros en una función: Refactorizado usando un objeto de parámetros (data class).

### 1.b Teniendo en cuenta aquella funcionalidad que tiene pruebas unitarias, selecciona un patrón de refactorización de los que has aplicado y que están cubierto por los test unitarios. ¿Porque mejora o no mejora tu código? Asegurate de poner enlaces a tu código
Selecciono el patrón de extraer función auxiliar aplicado en la función listarActividades, que está cubierto por pruebas unitarias (por ejemplo, los tests de obtención de detalles de actividades). Mejora el código porque reduce la complejidad, facilita el mantenimiento y la legibilidad.

[2]
### 2.a Describe el proceso que sigues para asegurarte que la refactorización no afecta a código que ya tenias desarrollado.
El proceso es:  
- Ejecutar todos los tests unitarios antes del refactor para asegurar que todo funciona.
- Aplicar la refactorización usando el IDE.
- Volver a ejecutar los tests unitarios después del refactor.
- Si todos los tests pasan, la refactorización no ha roto nada.

[3]
### 3.a ¿Que funcionalidad del IDE has usado para aplicar la refactorización seleccionada? Si es necesario, añade capturas de pantalla para identificar la funcionalidad.
He usado la funcionalidad de Refactorizar > Extraer función del IDE IntelliJ IDEA. Puedes identificarla en el menú contextual al seleccionar el bloque de código y elegir "Refactorizar" > "Extraer" > "Método". ![img_9.png](imagenes/img_9.png)


































