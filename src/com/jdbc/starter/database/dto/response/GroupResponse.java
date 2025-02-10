package com.jdbc.starter.database.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GroupResponse {

    @Schema(description = "Group ID", example = "1")
    private Long id;

    @Schema(description = "Group name", example = "Group A")
    private String name;

    @Schema(description = "Creation timestamp", example = "2024-02-09T12:30:00")
    private LocalDateTime createdAt;
}
