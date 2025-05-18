import AccesoDatos.RepoActividades
import Dominio.EstadoTarea
import Dominio.EtiquetasTareas
import Dominio.Tarea
import Presentacion.ConsolaUI
import Servicios.ActividadService
import Servicios.ControlDeHistorial
import Servicios.UsuariosService
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class ActividadServiceTest : DescribeSpec({

    val consolaMock = mockk<ConsolaUI>(relaxed = true)
    val repoMock = mockk<RepoActividades>(relaxed = true)
    val historialMock = mockk<ControlDeHistorial>(relaxed = true)
    val usuariosMock = mockk<UsuariosService>(relaxed = true)
    val service = ActividadService(consolaMock, repoMock, usuariosMock, historialMock)

    describe("agregarSubtarea") {
        beforeEach {
            clearMocks(consolaMock, repoMock, historialMock)
        }

        it("agrega una subtarea correctamente") {
            val tareaPrincipal = mockk<Tarea>(relaxed = true)
            every { repoMock.tareas } returns mutableListOf(tareaPrincipal)
            every { consolaMock.pedirInfo(any()) } returnsMany listOf("1", "Subtarea test", "UsuarioX")
            every { consolaMock.pedirEtiqueta() } returns EtiquetasTareas.URGENTE
            every { tareaPrincipal.getIdActividad() } returns "1"

            service.agregarSubtarea()

            verify { tareaPrincipal.agregarSubTarea(any()) }
            verify { historialMock.agregarHistorial("Subtarea agregada a la tarea 1") }
        }

        it("no agrega si la tarea principal no existe") {
            every { repoMock.tareas } returns mutableListOf()
            every { consolaMock.pedirInfo(any()) } returns "1"

            service.agregarSubtarea()

            verify(exactly = 0) { historialMock.agregarHistorial(any()) }
        }
    }

    describe("cambiarEstado") {
        beforeEach {
            clearMocks(consolaMock, historialMock)
        }

        it("modifica el estado de una tarea") {
            val tarea = mockk<Tarea>(relaxed = true)
            every { consolaMock.pedirInfo(any()) } returns "EN_PROGRESO"

            service.cambiarEstado(tarea)

            verify { tarea.actualizarEstado(EstadoTarea.EN_PROGRESO) }
            verify { historialMock.agregarHistorial("Estado de la tarea cambiado a EN_PROGRESO") }
        }

        it("no cambia si el estado es incorrecto") {
            val tarea = mockk<Tarea>(relaxed = true)
            every { consolaMock.pedirInfo(any()) } returns "INVALIDO"

            service.cambiarEstado(tarea)

            verify(exactly = 0) { tarea.actualizarEstado(any()) }
            verify(exactly = 0) { historialMock.agregarHistorial(any()) }
        }
    }

    describe("filtrarPorEstado") {
        beforeEach {
            clearMocks(consolaMock, repoMock, historialMock)
        }
        it("filtra tareas abiertas y termina el ciclo") {
            val tareaAbierta = mockk<Tarea>(relaxed = true)
            every { tareaAbierta.estado } returns EstadoTarea.ABIERTA
            every { repoMock.tareas } returns mutableListOf(tareaAbierta)
            every { consolaMock.pedirOpcion(any(), any(), any()) } returnsMany listOf(1, 0)

            service.filtrarPorEstado()

            verify { consolaMock.listarTareas(mutableListOf(tareaAbierta)) }
        }

        it("muestra mensaje si no hay tareas con ese estado y termina") {
            every { repoMock.tareas } returns mutableListOf()
            every { consolaMock.pedirOpcion(any(), any(), any()) } returnsMany listOf(1, 0)

            service.filtrarPorEstado()

            verify { consolaMock.mostrarMensaje("No hay tareas con el estado solicitado.") }
        }
    }

    describe("anadirActividad") {
        beforeEach {
            clearMocks(consolaMock, repoMock, historialMock)
        }

        it("añade una actividad correctamente") {
            val nuevaTarea = mockk<Tarea>(relaxed = true)
            every { consolaMock.pedirOpcion(any(), any(), any()) } returns 1
            every { consolaMock.crearActividad(any(), any(), any()) } returns nuevaTarea

            service.anadirActividad()

            verify { repoMock.aniadirActividad(nuevaTarea) }
            verify { historialMock.agregarHistorial("Actividad agregada con éxito") }
        }

        it("no añade si los datos son inválidos") {
            every { consolaMock.pedirOpcion(any(), any(), any()) } returns 1
            every { consolaMock.crearActividad(any(), any(), any()) } returns null

            service.anadirActividad()

            verify(exactly = 0) { repoMock.aniadirActividad(any()) }
            verify { historialMock.agregarHistorial("Error al crear actividad") }
        }
    }
})