package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;

public class AcademicDepartmentMapper {

    public static AcademicDepartment academicDepartmentCreateDtoToAcademicDepartment(AcademicDepartmentCreateDto academicDepartmentCreateDto) {
        AcademicDepartment academicDepartment = new AcademicDepartment();
        academicDepartment.setName(academicDepartmentCreateDto.getName());
        return academicDepartment;
    }

    public static AcademicDepartmentDto academicDepartmentToDto(AcademicDepartment academicDepartment
    , FacultyDto facultyDto) {
        AcademicDepartmentDto academicDepartmentDto = new AcademicDepartmentDto();
        academicDepartmentDto.setId(academicDepartment.getId());
        academicDepartmentDto.setName(academicDepartment.getName());
        academicDepartmentDto.setFaculty(facultyDto);
        return academicDepartmentDto;
    }

    public static AcademicDepartmentDto academicDepartmentToDto(AcademicDepartment academicDepartment) {
        AcademicDepartmentDto academicDepartmentDto = new AcademicDepartmentDto();
        academicDepartmentDto.setId(academicDepartment.getId());
        academicDepartmentDto.setName(academicDepartment.getName());
        return academicDepartmentDto;
    }
}
