package com.jdbc.starter.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Schema(hidden = true)
    private Long id;
    @Schema(description = "Student id")
    private Long studentId;
    @Schema(description = "Subject name")
    private String subject;
    @Schema(description = "Score")
    private Double score;
    @Schema(hidden = true)
    private LocalDate createdAt;

    public Grade(Long studentId, String subject, Double score, LocalDate createdAt) {
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
        this.createdAt = createdAt;
    }
}
