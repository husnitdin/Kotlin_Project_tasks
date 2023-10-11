package com.example.springboot_project_01.data.model

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

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