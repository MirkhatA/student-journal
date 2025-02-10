package com.jdbc.starter.database.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse {

    @Schema(description = "Student ID", example = "1")
    private Long id;

    @Schema(description = "First name", example = "Mirkhat")
    private String firstName;

    @Schema(description = "Last name", example = "Assen")
    private String lastName;

    @Schema(description = "Group id", example = "1")
    private Long groupId;

    @Schema(description = "Creation timestamp", example = "2024-02-09T12:30:00")
    private LocalDateTime createdAt;
}
