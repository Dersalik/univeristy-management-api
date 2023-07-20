package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;

public class AcademicDepartmentMapper {

    public static AcademicDepartment academicDepartmentCreateDtoToAcademicDepartment(AcademicDepartmentCreateDto academicDepartmentCreateDto) {
        AcademicDepartment academicDepartment = new AcademicDepartment();
        academicDepartment.setName(academicDepartmentCreateDto.getName());
        return academicDepartment;
    }

    public static AcademicDepartmentDto academicDepartmentToAcademicDepartmentDto(AcademicDepartment academicDepartment) {
        AcademicDepartmentDto academicDepartmentDto = new AcademicDepartmentDto();
        academicDepartmentDto.setId(academicDepartment.getId());
        academicDepartmentDto.setName(academicDepartment.getName());
        return academicDepartmentDto;
    }
}
