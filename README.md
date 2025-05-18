## INTALACIÓN Y COMANDO
![img_3.png](MEJORA-TASK/imagenes/img_3.png)
![img_2.png](MEJORA-TASK/imagenes/img_2.png)

## ERROR 1 
![Captura de pantalla 2025-05-18 182812.png](../../../Im%C3%A1genes/Capturas%20de%20pantalla/Captura%20de%20pantalla%202025-05-18%20182812.png)

![Captura de pantalla 2025-05-18 182649.png](../../../Im%C3%A1genes/Capturas%20de%20pantalla/Captura%20de%20pantalla%202025-05-18%20182649.png)

![Captura de pantalla 2025-05-18 182723.png](../../../Im%C3%A1genes/Capturas%20de%20pantalla/Captura%20de%20pantalla%202025-05-18%20182723.png)

El error ocurre porque hay demasiados niveles de código dentro de la función cargarActividades. Para arreglarlo, se puede sacar parte del código y ponerlo en otra función aparte. Así, el código principal queda más simple y fácil de manejar.


## ERROR 2

![img_4.png](MEJORA-TASK/imagenes/img_4.png)

![img_5.png](MEJORA-TASK/imagenes/img_5.png)

![img_6.png](MEJORA-TASK/imagenes/img_6.png)

El error indica que la función tiene demasiados parámetros. Para solucionarlo, agrupa los parámetros relacionados en una data class y pásala como argumento.


## ERROR 3

![img_7.png](MEJORA-TASK/imagenes/img_7.png)

![img_8.png](MEJORA-TASK/imagenes/img_8.png)

![img_9.png](MEJORA-TASK/imagenes/img_9.png)

El problema se debe a la anidación de bloques dentro de listarActividades. Para solucionarlo, extrae la lógica de impresión de subtareas a una función auxiliar. Así reduces la profundidad y mejoras la legibilidad.


## ERROR 4

![img_11.png](MEJORA-TASK/imagenes/img_11.png)

![img_10.png](MEJORA-TASK/imagenes/img_10.png)

![img_12.png](MEJORA-TASK/imagenes/img_12.png)

El problema de anidamiento se soluciona extrayendo la lógica de impresión de actividades del usuario a una función auxiliar. Así se reduce la profundidad de la función principal.


## ERROR 5

![img_17.png](MEJORA-TASK/imagenes/img_17.png)

![img_18.png](MEJORA-TASK/imagenes/img_18.png)

![img_19.png](MEJORA-TASK/imagenes/img_19.png)

El problema de anidamiento en usuariosConActividades se soluciona extrayendo la lógica de asociación de actividades a una función auxiliar. Así se reduce la profundidad y mejora la legibilidad.


![img.png](img.png)

En esta línea de código "baseline = file("config/detekt/detekt.xml")" se establece la ruta del archivo de configuración de Detekt. Este archivo contiene las reglas y configuraciones personalizadas que se aplicarán durante el análisis del código. Al especificar esta ruta, se asegura que Detekt guarde un registro de los problemas detectados y pueda comparar el código actual con el estado anterior, facilitando la identificación de nuevos problemas y la mejora continua del código.