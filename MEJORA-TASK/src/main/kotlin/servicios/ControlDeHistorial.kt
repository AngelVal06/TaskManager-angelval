package servicios

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDateTime
/**
 * Clase para el control y registro del historial de operaciones del sistema.
 * Permite registrar mensajes tanto en un archivo como mediante un logger.
 *
 * @property logger Logger SLF4J para registro de eventos
 */
open class ControlDeHistorial {

    private val logger: Logger = LoggerFactory.getLogger(ControlDeHistorial::class.java)

    /**
     * Agrega una entrada al historial del sistema.
     * Registra el mensaje tanto en el logger como en el archivo de historial.
     *
     * @param msj Mensaje a registrar en el historial
     * @throws IOException Si ocurre un error al escribir en el archivo
     */
    fun agregarHistorial(msj: String) {
        val log = "${LocalDateTime.now()} -> $msj"
        logger.info(log) // Registrar en el logger
        try {
            File(RUTA_HISTORIAL).appendText("$log\n")
        } catch (e: Exception) {
            logger.error("Error al escribir en el historial: ${e.message}", e)
        }
    }

    companion object {
        /**
         * Ruta del archivo donde se almacena el historial de operaciones.
         */
        const val RUTA_HISTORIAL = "MEJORA-TASK/src/main/kotlin/Datos/Historial.txt"
    }
}