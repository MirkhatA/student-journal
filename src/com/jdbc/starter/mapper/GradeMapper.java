package com.jdbc.starter.mapper;

import com.jdbc.starter.database.dto.request.GradeRequest;
import com.jdbc.starter.database.dto.response.GradeResponse;
import com.jdbc.starter.database.entity.Grade;

import java.time.LocalDate;

public class GradeMapper {

    public static Grade toEntity(GradeRequest request) {
        return new Grade(
                request.getStudentId(),
                request.getSubject(),
                request.getScore(),
                LocalDate.now()
        );
    }

    public static GradeResponse toResponse(Grade grade) {
        return new GradeResponse(
                grade.getId(),
                grade.getStudentId(),
                grade.getSubject(),
                grade.getScore(),
                grade.getCreatedAt()
        );
    }
}
