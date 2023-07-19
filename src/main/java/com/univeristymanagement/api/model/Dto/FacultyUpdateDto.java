package com.univeristymanagement.api.model.Dto;

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
public class FacultyUpdateDto {
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    private String name;
    @NotNull(message = "Description is required")
    @Size(min = 2, max = 1000, message = "Description should be between 2 and 1000 characters")
    private String description;
    @NotNull(message = "Established date is required")
    @Past(message = "Established date should be in the past")
    private LocalDate establishedDate;
    @NotNull(message = "Founder is required")
    @Size(min = 2, max = 70, message = "Founder should be between 2 and 70 characters")
    private String founder;

}
