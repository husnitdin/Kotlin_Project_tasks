package com.example.task_app_be_mediumcom.service

import com.example.springboot_project_01.data.Task
import com.example.springboot_project_01.data.model.TaskCreateRequest
import com.example.springboot_project_01.data.model.TaskDto
import com.example.springboot_project_01.data.model.TaskUpdateRequest
import com.example.springboot_project_01.exception.BadRequestException
import com.example.springboot_project_01.exception.TaskNotFoundException
import com.example.springboot_project_01.repository.TaskRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field

/**
 * Author: husnitdin@gmail.com
 * Date: 11/10/23
 * Time: 16:10
 */

@Service
class TaskService(private val repository: TaskRepository) {

    private fun convertEntityToDto(task: Task): TaskDto {
        return TaskDto(
            task.id,
            task.description,
            task.isReminderSet,
            task.isTaskOpen,
            task.createdOn,
            task.priority
        )
    }

    private fun assignValuesToEntity(task: Task, taskRequest: TaskCreateRequest) {
        task.description = taskRequest.description
        task.isReminderSet = taskRequest.isReminderSet
        task.isTaskOpen = taskRequest.isTaskOpen
        task.createdOn = taskRequest.createdOn
        task.priority = taskRequest.priority
    }

    private fun checkForTaskId(id: Long) {
        if (!repository.existsById(id)) {
            throw TaskNotFoundException("Task with ID: $id does not exist!")
        }
    }

    fun getAllTasks(): List<TaskDto> =
        repository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getAllOpenTasks(): List<TaskDto> =
        repository.queryAllOpenTasks().stream().map(this::convertEntityToDto).collect(Collectors.toList())

    fun getAllClosedTasks(): List<TaskDto> =
        repository.queryAllClosedTasks().stream().map(this::convertEntityToDto).collect(Collectors.toList())


    fun getTaskById(id: Long): TaskDto {
        checkForTaskId(id)
        val task: Task = repository.findTaskById(id)
        return convertEntityToDto(task)
    }

    fun createTask(createRequest: TaskCreateRequest): TaskDto {
        if (repository.doesDescriptionExist(createRequest.description)) {
            throw BadRequestException("There is already a task with description: ${createRequest.description}")
        }
        val task = Task()
        assignValuesToEntity(task, createRequest)
        val savedTask = repository.save(task)
        return convertEntityToDto(savedTask)
    }

    fun updateTask(id: Long, updateRequest: TaskUpdateRequest): TaskDto {
        checkForTaskId(id)
        val existingTask: Task = repository.findTaskById(id)

        for (item in TaskUpdateRequest::class.memberProperties) {
            if (item.get(updateRequest) != null) {
                val field: Field? = ReflectionUtils.findField(Task::class.java, item.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingTask, item.get(updateRequest))
                }
            }
        }

        val savedTask: Task = repository.save(existingTask)
        return convertEntityToDto(savedTask)
    }

    fun deleteTask(id: Long): String {
        checkForTaskId(id)
        repository.deleteById(id)
        return "Task with id: $id has been deleted."
    }
}