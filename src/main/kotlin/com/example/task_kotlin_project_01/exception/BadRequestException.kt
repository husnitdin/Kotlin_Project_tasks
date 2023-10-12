package com.example.springboot_project_01.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
/**
 * Author: husnitdin@gmail.com
 * Date: 11/10/23
 * Time: 15:25
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
data class BadRequestException(override val message: String): RuntimeException()