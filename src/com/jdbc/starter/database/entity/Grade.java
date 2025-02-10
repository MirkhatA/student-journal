package com.jdbc.starter.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    private Long id;
    private Long studentId;
    private String subject;
    private Double score;
    private LocalDate createdAt;

    public Grade(Long studentId, String subject, Double score, LocalDate createdAt) {
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
        this.createdAt = createdAt;
    }
}
