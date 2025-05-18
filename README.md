
## IDENTIFICACIÓN DE MÉTODOS PÚBLICOS

| Método                        | Parámetros de entrada                                                    | Resultado esperado o efecto en el repositorio                     |
| ----------------------------- | ------------------------------------------------------------------------ | ----------------------------------------------------------------- |
| `agregarSubtarea()`           | Entradas por consola: ID tarea principal, descripción, usuario, etiqueta | Se agrega una subtarea a una tarea existente, se guarda historial |
| `cambiarEstado(tarea: Tarea)` | Estado nuevo por consola                                                 | Actualiza el estado de la tarea, guarda historial                 |
| `filtrarPorEstado()`          | Selección de estado por consola                                          | Lista tareas que coincidan con el estado                          |
| `anadirActividad()`           | Parámetros por consola (tipo, usuario, etc.)                             | Se crea y guarda nueva actividad en el repo, historial            |


## TABLA PRUEBAS

| Método             | Caso de prueba                  | Estado inicial del mock                      | Acción          | Resultado esperado                                             |
| ------------------ | ------------------------------- | -------------------------------------------- | --------------- | -------------------------------------------------------------- |
| agregarSubtarea()  | ID válido y datos válidos       | `repoMock.tareas` contiene tarea con ID      | Ejecutar método | Subtarea añadida, historial actualizado                        |
| agregarSubtarea()  | ID no existe                    | `repoMock.tareas` vacío                      | Ejecutar método | No se agrega nada, historial **no** llamado                    |
| cambiarEstado(t)   | Estado válido                   | Entrada "EN\_PROGRESO"                       | Ejecutar método | `tarea.actualizarEstado(...)`, historial actualizado           |
| cambiarEstado(t)   | Estado inválido                 | Entrada "INVALIDO"                           | Ejecutar método | No se cambia estado, historial **no** llamado                  |
| filtrarPorEstado() | Tareas existen con ese estado   | `repoMock.tareas` con una tarea abierta      | Ejecutar método | `consola.listarTareas(...)` llamada con lista filtrada         |
| filtrarPorEstado() | Ninguna tarea tiene ese estado  | `repoMock.tareas` vacío                      | Ejecutar método | Se muestra mensaje: "No hay tareas con el estado solicitado."  |
| anadirActividad()  | Datos válidos, actividad creada | `consola.crearActividad(...)` devuelve tarea | Ejecutar método | Se llama a `repo.aniadirActividad(...)`, historial actualizado |
| anadirActividad()  | Datos inválidos, actividad nula | `consola.crearActividad(...)` devuelve null  | Ejecutar método | No se llama a repo, historial con mensaje de error             |


## PRUEBAS UNITARIAS (FOTO)
![img.png](img.png)