package com.univeristymanagement.api.model.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class StudyProgramDto {


    private Long id;
    private String name;
    private String description;
    private int capacity;
    AcademicDto academicDto;
    AcademicDepartmentDto academicDepartmentDto;

}
