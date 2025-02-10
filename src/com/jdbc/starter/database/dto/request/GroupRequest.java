package com.jdbc.starter.database.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {

    @Schema(description = "Group name", example = "Group A")
    @NotBlank(message = "Group name cannot be empty")
    private String name;
}
