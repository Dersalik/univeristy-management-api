package com.univeristymanagement.api.model.Dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDepartmentDto {
    private Long id;
    private String name;
    private FacultyDto faculty;
}
