package dominio
/**
 * Clase que representa una tarea en el sistema, heredando de Actividad.
 * Permite gestionar subtareas y estados de la tarea.
 *
 * @property etiqueta Clasificación de la tarea (URGENTE, SENCILLA, etc.)
 * @property estado Estado actual de la tarea (ABIERTA, EN_PROGRESO, FINALIZADA)
 * @property subTareas Lista de subtareas asociadas a esta tarea
 */
class Tarea private constructor(
    descripcion: String,
    usuario: String,
    val etiqueta: EtiquetasTareas
) : Actividad(descripcion, usuario) {

    init{
        contador += 1
    }
    var estado = EstadoTarea.ABIERTA

    // Lista de subtareas asociadas
    val subTareas: MutableList<Tarea> = mutableListOf()

    private constructor(
        usuario: String,
        id: String,
        etiqueta: EtiquetasTareas,
        fechaCreacion: String,
        descripcion: String,
        estado: String
    ) : this(usuario, descripcion, etiqueta) {
        this.id = id + contador
        this.fechaCreacion = fechaCreacion
        this.estado = EstadoTarea.getEstado(estado)!!
    }

    override fun obtenerDetalle(): String {
        val subTareasDetalle = if (subTareas.isEmpty()) {
            "Sin subtareas"
        } else {
            subTareas.joinToString(separator = "\n") { "    - ${it.obtenerDetalle()}" }
        }
        return super.obtenerDetalle() + ";$estado;$etiqueta;\nSubtareas:\n$subTareasDetalle"
    }

    /**
     * Actualiza el estado de la tarea y todas sus subtareas.
     *
     * @param estado Nuevo estado a asignar
     */
    fun actualizarEstado(estado: EstadoTarea) {
        this.estado = estado
        for(tarea in subTareas) {
            tarea.estado = estado
        }
    }

    /**
     * Agrega una subtarea a esta tarea.
     *
     * @param subTarea Subtarea a agregar
     * @throws IllegalArgumentException Si la subtarea ya tiene subtareas propias
     */
    fun agregarSubTarea(subTarea: Tarea) {
        if (!subTareas.contains(subTarea)) { // Evitar duplicados en la lista de subtareas
            // Validar que las subtareas no puedan tener más subtareas
            if (subTarea.subTareas.isNotEmpty()) {
                throw IllegalArgumentException("Una subtarea no puede tener más subtareas.")
            }
            subTareas.add(subTarea)
        } else {
            println("La subtarea ya existe, no se añadirá de nuevo.")
        }
    }

    companion object {
        /**
         * Contador estático para generación de IDs únicos.
         */
        var contador = 0

        /**
         * Crea una nueva instancia de Tarea con los parámetros básicos.
         *
         * @param descripcion Descripción de la tarea
         * @param usuario Usuario asignado a la tarea
         * @param etiqueta Clasificación de la tarea
         * @return Nueva instancia de Tarea
         */
        fun creaInstancia(descripcion: String, usuario: String, etiqueta: EtiquetasTareas): Tarea {
            return Tarea(descripcion, usuario, etiqueta)
        }

        fun creaInstancia(
            usuario: String,
            id: String,
            etiqueta: EtiquetasTareas,
            fechaCreacion: String,
            descripcion: String,
            estado: String
        ): Tarea {
            return Tarea(usuario, id, etiqueta, fechaCreacion, descripcion, estado)
        }
    }
}