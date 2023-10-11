package com.example.springboot_project_01.data.model

/*
This class will be used when an already existing task,
should be updated here we never want to update the `id`
or `createdOn` field, that's why I excluded those fields.
This class will be used later with a PATCH endpoint that's
why all fields are nullable. Because the client can choose
which fields to update, the client doesn’t need to update
all fields like with a PUT endpoint.
 */

data class TaskUpdateRequest(
    val description: String?,
    val isReminderSet: Boolean?,
    val isTaskOpen: Boolean?,
    val priority: Priority?
)