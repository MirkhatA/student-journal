package com.jdbc.starter.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Schema(hidden = true)
    private Long id;
    @Schema(description = "First name")
    private String firstName;
    @Schema(description = "Last name")
    private String lastName;
    @Schema(description = "Group id")
    private Long groupId;
    @Schema(hidden = true)
    private LocalDateTime createdAt;

    public Student(String firstName, String lastName, Long groupId, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
        this.createdAt = createdAt;
    }
}
