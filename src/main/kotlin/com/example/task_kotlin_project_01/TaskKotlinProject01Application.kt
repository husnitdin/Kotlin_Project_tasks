package com.example.task_kotlin_project_01

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class TaskKotlinProject01Application

fun main(args: Array<String>) {
    runApplication<TaskKotlinProject01Application>(*args)
    println("Kotlin Code is running")
}


