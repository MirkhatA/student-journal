package com.jdbc.starter.database.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeRequest {

    @Schema(description = "Student id", example = "1")
    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @Schema(description = "Subject name", example = "Math")
    @NotNull(message = "Subject cannot be null")
    private String subject;

    @Schema(description = "Score", example = "95")
    @NotNull(message = "Score cannot be null")
    private Double score;
}
