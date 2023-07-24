package com.univeristymanagement.api.model.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyCreateDto {
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    @Schema(description = "Name of the faculty", example = "Computer Science", required = true)
    private String name;

    @Schema(description = "Description of the faculty", example = "Computer Science is a field ....", required = true)
    @NotNull(message = "Description is required")
    @Size(min = 2, max = 1000, message = "Description should be between 2 and 1000 characters")
    private String description;

    @Schema(description = "Established date of the faculty", example = "2020-01-01", required = true)
    @NotNull(message = "Established date is required")
    @Past(message = "Established date should be in the past")
    private LocalDate establishedDate;

    @Schema(description = "Founder of the faculty", example = "John Doe", required = true)
    @NotNull(message = "Founder is required")
    @Size(min = 2, max = 70, message = "Founder should be between 2 and 70 characters")
    private String founder;

}
