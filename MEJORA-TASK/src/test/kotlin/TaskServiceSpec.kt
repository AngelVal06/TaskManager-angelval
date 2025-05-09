import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk
import java.time.LocalDateTime
import java.util.Optional
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBeNull
import io.kotest.assertions.throwables.shouldThrow
import io.mockk.every
import io.mockk.verify



class TaskServiceSpec : DescribeSpec({
    val mockTaskRepository = mockk<TaskRepository>()
    val taskService = TaskService(mockTaskRepository)

    describe("TaskService") {
        describe("createTask") {
            context("con datos válidos") {
                it("debe crear y retornar una nueva tarea") {
                    val taskDto = TaskDto("Tarea test", "Descripción test", false)
                    val savedTask = Task(1, "Tarea test", "Descripción test", false, LocalDateTime.now())
                    every { mockTaskRepository.save(any()) } returns savedTask

                    val result = taskService.createTask(taskDto)

                    result.shouldNotBeNull()
                    result.id shouldBe 1
                }
            }
        }
    }
})


class TaskService
    val taskService = TaskService(mockTaskRepository)

    describe("TaskService") {
        describe("createTask") {
            context("con datos válidos") {
                it("debe crear y retornar una nueva tarea") {
                    val taskDto = TaskDto("Tarea test", "Descripción test", false)
                    val savedTask = Task(1, "Tarea test", "Descripción test", false, LocalDateTime.now())
                    every { mockTaskRepository.save(any()) } returns savedTask

                    val result = taskService.createTask(taskDto)

                    result.shouldNotBeNull()
                    result.id shouldBe 1
                }
                    every { mockTaskRepository.save(any()) } returns savedTask

                    val result = taskService.createTask(taskDto)

                    result.shouldNotBeNull()
                    result.id shouldBe 1
                    verify { mockTaskRepository.save(any()) }
                }
            }
        }

        describe("getAllTasks") {
            context("cuando hay tareas") {
                it("debe retornar lista de tareas") {
                    val tasks = listOf(
                        Task(1, "Tarea 1", "Desc 1", false, LocalDateTime.now()),
                        Task(2, "Tarea 2", "Desc 2", true, LocalDateTime.now())
                    )

                    every { mockTaskRepository.findAll() } returns tasks

                    val result = taskService.getAllTasks()

                    result.size shouldBe 2
                }
            }

            context("cuando no hay tareas") {
                it("debe retornar lista vacía") {
                    every { mockTaskRepository.findAll() } returns emptyList()

                    val result = taskService.getAllTasks()

                    result.isEmpty() shouldBe true
                }
            }
        }

        describe("getTaskById") {
            context("con ID existente") {
                it("debe retornar la tarea") {
                    val task = Task(1, "Tarea test", "Desc test", false, LocalDateTime.now())
                    every { mockTaskRepository.findById(1) } returns Optional.of(task)

                    val result = taskService.getTaskById(1)

                    result.shouldNotBeNull()
                    result.id shouldBe 1
                }
            }

            context("con ID inexistente") {
                it("debe lanzar TaskNotFoundException") {
                    every { mockTaskRepository.findById(999) } returns Optional.empty()

                    shouldThrow<TaskNotFoundException> {
                        taskService.getTaskById(999)
                    }
                }
            }
        }

        describe("updateTask") {
            context("con tarea existente") {
                it("debe actualizar y retornar la tarea") {
                    val existingTask = Task(1, "Original", "Desc", false, LocalDateTime.now())
                    val updatedDto = TaskDto("Actualizada", "Nueva desc", true)

                    every { mockTaskRepository.findById(1) } returns Optional.of(existingTask)
                    every { mockTaskRepository.save(any()) } returnsArgument 0

                    val result = taskService.updateTask(1, updatedDto)

                    result.title shouldBe "Actualizada"
                    result.description shouldBe "Nueva desc"
                    result.completed shouldBe true
                }
            }

            context("con tarea inexistente") {
                it("debe lanzar TaskNotFoundException") {
                    every { mockTaskRepository.findById(999) } returns Optional.empty()

                    shouldThrow<TaskNotFoundException> {
                        taskService.updateTask(999, TaskDto("Tarea", "Desc", false))
                    }
                }
            }
        }

        describe("deleteTask") {
            context("con ID existente") {
                it("debe eliminar y retornar true") {
                    every { mockTaskRepository.existsById(1) } returns true
                    every { mockTaskRepository.deleteById(1) } returns Unit

                    val result = taskService.deleteTask(1)

                    result shouldBe true
                    verify { mockTaskRepository.deleteById(1) }
                }
            }

            context("con ID inexistente") {
                it("debe retornar false") {
                    every { mockTaskRepository.existsById(999) } returns false

                    val result = taskService.deleteTask(999)

                    result shouldBe false
                }
            }
        }
    }
})