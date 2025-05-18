
## Respuesta a las preguntas:

[1]
### 1.a ¿Que herramienta has usado, y para que sirve?

He usado la herramienta Detekt. Sirve para analizar el código Kotlin y encontrar problemas de estilo, errores comunes y malas prácticas.

### 1.b ¿Cuales son sus características principales?

Sus características principales son:
- Detecta errores de estilo y calidad en código Kotlin.
- Permite personalizar reglas y umbrales (por ejemplo, máximo de parámetros en una función).
- Se integra fácilmente con Gradle e IDEs.
- Genera informes claros de los problemas encontrados.

### 1.c ¿Qué beneficios obtengo al utilizar dicha herramienta?

Los beneficios son:
- Código más limpio y fácil de mantener.
- Encuentra errores antes de que causen problemas.
- Ayuda a seguir buenas prácticas automáticamente.

[2]
### 2.a De los errores/problemas que la herramienta ha detectado y te ha ayudado a solucionar, ¿cual es el que te ha parecido que ha mejorado más tu código?

El error que más ha mejorado mi código es el de funciones con demasiados parámetros.

### 2.b ¿La solución que se le ha dado al error/problema la has entendido y te ha parecido correcta?

Sí, la solución (usar una data class para agrupar parámetros) la he entendido y me parece correcta porque hace el código más legible y fácil de usar.

### 2.c ¿Por qué se ha producido ese error/problema?

El error se produce porque tener muchos parámetros en una función la hace difícil de leer, mantener y usar correctamente.

[3]
### 3.a ¿Que posibilidades de configuración tiene la herramienta?

Se puede configurar:
- Qué reglas están activas o desactivadas.
- Los umbrales de cada regla (por ejemplo, máximo de funciones en una clase, máximo de parámetros, etc).
- Excepciones y rutas a ignorar.

### 3.b De esas posibilidades de configuración, ¿cuál has configurado para que sea distinta a la que viene por defecto?

He configurado el umbral de máximo de parámetros en una función a 6, que es distinto al valor por defecto.

### 3.c Pon un ejemplo de como ha impactado en tu código, enlazando al código anterior al cambio, y al posterior al cambio,

Antes:
fun creaInstancia(usuario: String, id: String, descripcion: String, fechaCreacion: String, fecha: String, ubicacion: String)

Después:
data class DatosTarea(val usuario: String, val id: String, val descripcion: String, val fechaCreacion: String, val fecha: String, val ubicacion: String)
fun creaInstancia(datos: DatosTarea)

[4]
### 4 ¿Qué conclusiones sacas después del uso de estas herramientas?

Las conclusiones son que estas herramientas ayudan mucho a mantener el código ordenado, evitan errores tontos y te obligan a seguir buenas prácticas, lo que hace que el proyecto sea más fácil de mantener y mejorar.