package com.jdbc.starter.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private Long groupId;
    private LocalDateTime createdAt;

    public Student(String firstName, String lastName, Long groupId, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
        this.createdAt = createdAt;
    }
}
