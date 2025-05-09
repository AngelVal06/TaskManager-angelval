package com.taskmanager.angelval.taskmanager

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional
import kotlin.NoSuchElementException
import com.taskmanager.angelval.taskmanager.model.Task
import com.taskmanager.angelval.taskmanager.repository.TaskRepository
import com.taskmanager.angelval.taskmanager.service.TaskService
import org.mockito.kotlin.*
import java.util.*

package com.taskmanager.angelval.taskmanager.service

import com.taskmanager.angelval.taskmanager.model.Task
import com.taskmanager.angelval.taskmanager.repository.TaskRepository
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class TaskServiceSpec {

    @Mock
    private lateinit var taskRepository: TaskRepository

    @InjectMocks
    private lateinit var taskService: TaskService

    private lateinit var task: Task

    @BeforeEach
    fun setUp() {
        task = Task(
            id = 1L,
            title = "Test Task",
            description = "Test Description",
            completed = false
        )
    }

    @Test
    fun `should get all tasks`() {
        // Given
        val expectedTasks = listOf(task)
        whenever(taskRepository.findAll()).thenReturn(expectedTasks)

        // When
        val actualTasks = taskService.getAllTasks()

        // Then
        assertEquals(expectedTasks, actualTasks)
        verify(taskRepository, times(1)).findAll()
    }

    @Test
    fun `should get task by id`() {
        // Given
        whenever(taskRepository.findById(1L)).thenReturn(Optional.of(task))

        // When
        val foundTask = taskService.getTaskById(1L)

        // Then
        assertTrue(foundTask.isPresent)
        assertEquals(task, foundTask.get())
        verify(taskRepository, times(1)).findById(1L)
    }

    @Test
    fun `should create task`() {
        // Given
        whenever(taskRepository.save(any())).thenReturn(task)

        // When
        val createdTask = taskService.createTask(task)

        // Then
        assertNotNull(createdTask)
        assertEquals(task, createdTask)
        verify(taskRepository, times(1)).save(task)
    }

    @Test
    fun `should update task`() {
        // Given
        val updatedTask = task.copy(
            title = "Updated Title",
            description = "Updated Description",
            completed = true
        )

        whenever(taskRepository.findById(1L)).thenReturn(Optional.of(task))
        whenever(taskRepository.save(any())).thenReturn(updatedTask)

        // When
        val result = taskService.updateTask(1L, updatedTask)

        // Then
        assertNotNull(result)
        assertEquals("Updated Title", result.title)
        assertEquals("Updated Description", result.description)
        assertTrue(result.completed)
        verify(taskRepository, times(1)).findById(1L)
        verify(taskRepository, times(1)).save(any())
    }

    @Test
    fun `should delete task`() {
        // Given
        doNothing().whenever(taskRepository).deleteById(1L)

        // When
        taskService.deleteTask(1L)

        // Then
        verify(taskRepository, times(1)).deleteById(1L)
    }

    @Test
    fun `should throw exception when updating non-existent task`() {
        // Given
        whenever(taskRepository.findById(any())).thenReturn(Optional.empty())

        // When / Then
        assertThrows(NoSuchElementException::class.java) {
            taskService.updateTask(99L, task)
        }
        verify(taskRepository, never()).save(any())
    }
}