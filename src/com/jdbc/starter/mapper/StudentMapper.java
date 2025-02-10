package com.jdbc.starter.mapper;

import com.jdbc.starter.database.dto.request.StudentRequest;
import com.jdbc.starter.database.dto.response.StudentResponse;
import com.jdbc.starter.database.entity.Student;

import java.time.LocalDateTime;

public class StudentMapper {

    public static Student toEntity(StudentRequest request) {
        return new Student(
                request.getFirstName(),
                request.getLastName(),
                request.getGroupId(),
                LocalDateTime.now()
        );
    }

    public static StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGroupId(),
                student.getCreatedAt()
        );
    }
}
