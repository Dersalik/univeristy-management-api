package com.univeristymanagement.api.model.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyProgramUpdateDto {

    @NotNull(message = "Name is required")
    @Schema(description = "Name of the study program", example = "Computer Science", required = true)
    private String name;
    @NotNull(message = "Description is required")
    @Schema(description = "Description of the study program", example = "Computer Science is a field ....", required = true)
    private String description;
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be greater than 0")
    @Max(value = 100, message = "Capacity must be less than 100")
    @Schema(description = "Capacity of the study program", example = "50", required = true)
    private int capacity;
    @NotNull(message = "Academic  id is required")
    @Schema(description = "Academic id of the study program", example = "1", required = true)
    private Long academicId;
    @Schema(description = "Department id of the study program", example = "1", required = true)
    @NotNull(message = "Department id is required")
    private Long departmentId;
}
