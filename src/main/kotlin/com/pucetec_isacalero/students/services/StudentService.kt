package com.pucetec_isacalero.students.services

import com.pucetec_isacalero.students.dto.StudentRequest
import com.pucetec_isacalero.students.dto.StudentResponse
import com.pucetec_isacalero.students.entities.Student
import com.pucetec_isacalero.students.exceptions.EmailAlreadyExistsException
import com.pucetec_isacalero.students.mappers.toEntity
import com.pucetec_isacalero.students.mappers.toResponse
import com.pucetec_isacalero.students.repositories.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository
){
    private val logger = LoggerFactory.getLogger(StudentService::class.java)

    fun createdStudent(request: StudentRequest): StudentResponse{
        logger.info("Creating Student ${request.name}")

        if (studentRepository.existsByEmail(request.email)) {
            logger.warn("Attempted to register duplicate email: ${request.email}")
            throw EmailAlreadyExistsException("El correo ${request.email} ya está registrado.")
        }

        val studentToSave = request.toEntity()

        val savedStudent = studentRepository.save(studentToSave)
        logger.info("Save student with id ${savedStudent.id}")

        return savedStudent.toResponse()
    }

    open fun getAllStudents(): List<StudentResponse>{
        logger.info("Get all Students")

        val savedStudents = studentRepository.findAll()

        return savedStudents.map { it.toResponse() }
    }
}