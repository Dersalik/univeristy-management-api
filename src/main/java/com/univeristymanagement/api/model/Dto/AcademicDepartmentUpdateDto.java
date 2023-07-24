package com.univeristymanagement.api.model.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDepartmentUpdateDto {

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    @Schema(description = "Name of the academic department", example = "Computer Science", required = true)
    private String name;
}
