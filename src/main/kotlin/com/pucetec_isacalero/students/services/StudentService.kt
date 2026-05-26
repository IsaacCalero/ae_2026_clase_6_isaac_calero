package com.pucetec_isacalero.students.services

import com.pucetec_isacalero.students.dto.StudentRequest
import com.pucetec_isacalero.students.dto.StudentResponse
import com.pucetec_isacalero.students.entities.Student
import com.pucetec_isacalero.students.repositories.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository
) {
    private val logger = LoggerFactory.getLogger(StudentService::class.java)

    fun createStudent(request: StudentRequest): StudentResponse {
        logger.info("Creating Student ${request.name}")

        val studentEntity = Student(
            name = request.name,
            email = request.email
        )

        val savedStudent = studentRepository.save(studentEntity)

        return StudentResponse(
            id = savedStudent.id,
            name = savedStudent.name,
            email = savedStudent.email
        )
    }

    open fun getAllStudents(): List<StudentResponse> {
        logger.info("Getting all students")


        val saveStudents= studentRepository.findAll()

        return saveStudents.map{
            StudentResponse(
                id = it.id,
                name = it.name,
                email = it.email
            )
        }
    }
}