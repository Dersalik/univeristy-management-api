package com.univeristymanagement.api.model.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String preNominalTitles;
    private String postNominalTitles;
    private AcademicDepartmentDto academicDepartmentDto;

}
