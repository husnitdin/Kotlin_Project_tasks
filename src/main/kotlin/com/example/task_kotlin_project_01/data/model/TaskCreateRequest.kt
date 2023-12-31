package com.example.springboot_project_01.data.model

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

/**
 * Author: husnitdin@gmail.com
 * Date: 11/10/23
 * Time: 14:45
 */

/*
This class will be used when a task gets created,
so when a POST request is sent to the backend.
Here we will need every field except for ID
because the task ID will be created not by the client,
it will be created by JPA.
 */

data class TaskCreateRequest(
    @NotBlank(message = "description can't be empty")
    val description: String,

    val isReminderSet: Boolean,

    val isTaskOpen: Boolean,

    @NotBlank(message = "created_on can't be empty")
    val createdOn: LocalDateTime,

    val priority: Priority
)