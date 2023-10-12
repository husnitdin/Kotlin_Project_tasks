package com.example.springboot_project_01.data.model

import java.time.LocalDateTime
/**
 * Author: husnitdin@gmail.com
 * Date: 11/10/23
 * Time: 13:50
 */
data class TaskDto(
    val id: Long,
    val description: String,
    val isReminderSet: Boolean,
    val isTaskOpen: Boolean,
    val createdOn: LocalDateTime,
    val priority: Priority
)