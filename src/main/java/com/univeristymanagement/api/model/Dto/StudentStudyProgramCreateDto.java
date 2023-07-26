package com.univeristymanagement.api.model.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentStudyProgramCreateDto {
    @NotNull(message = "EnrollmentDate is required")
    private LocalDate EnrollmentDate;
    @NotNull (message = "GraduationDate is required")
    private LocalDate GraduationDate;
    @NotNull(message = "isActive is required")
    private boolean isActive;

}
