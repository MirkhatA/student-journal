package com.jdbc.starter.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Schema(hidden = true)
    private Long id;
    @Schema(description = "Group name")
    private String name;
    @Schema(hidden = true)
    private LocalDateTime createdAt;

    public Group(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }
}
