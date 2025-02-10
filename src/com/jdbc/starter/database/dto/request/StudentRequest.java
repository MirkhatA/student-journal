package com.jdbc.starter.database.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    @Schema(description = "First name", example = "Mirkhat")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Schema(description = "Last name", example = "Assen")
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Schema(description = "Group id", example = "1")
    @NotNull(message = "Group ID cannot be null")
    private Long groupId;
}
