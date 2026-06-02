package com.pucetec_isacalero.students.controllers

import com.pucetec_isacalero.students.dto.StudentRequest
import com.pucetec_isacalero.students.dto.StudentResponse
import com.pucetec_isacalero.students.services.StudentService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController (
    val studentService: StudentService,
){
    private val logger = LoggerFactory.getLogger(StudentController::class.java)

    @PostMapping("/api/students")
    open fun createdStudent(
        @RequestBody
        request: StudentRequest
    ): StudentResponse{
        logger.info("Creating Student ${request.name}")
        return studentService.createdStudent(request)
    }

    @GetMapping("/api/students")
    open fun getAllStudents(): List<StudentResponse>{
        logger.info("Getting all students")
        return studentService.getAllStudents()
    }
}