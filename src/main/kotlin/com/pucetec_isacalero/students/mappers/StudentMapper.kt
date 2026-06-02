package com.pucetec_isacalero.students.mappers

import com.pucetec_isacalero.students.dto.StudentRequest
import com.pucetec_isacalero.students.dto.StudentResponse
import com.pucetec_isacalero.students.entities.Student

fun StudentRequest.toEntity(): Student {
    return Student(
        name = name,
        email = email
    )
}

fun Student.toResponse(): StudentResponse {
    return StudentResponse(
        id = this.id,
        name = this.name,
        email = this.email ?: ""
    )
}