package com.jdbc.starter.database.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GradeResponse {

    @Schema(description = "Grade ID", example = "1")
    private Long id;

    @Schema(description = "Student ID", example = "1")
    private Long studentId;

    @Schema(description = "Subject name", example = "Math")
    private String subject;

    @Schema(description = "Score", example = "95.0")
    private Double score;

    @Schema(description = "Creation timestamp", example = "2024-02-09T12:30:00")
    private LocalDate createdAt;

}
