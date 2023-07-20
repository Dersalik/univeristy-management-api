package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDepartmentCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentUpdateDto;

import java.util.List;

public interface AcademicDepartmentService {

    List<AcademicDepartmentDto> getAllAcademicDepartments();
    AcademicDepartmentDto createAcademicDepartment(AcademicDepartmentCreateDto academicDepartmentDto);
    AcademicDepartmentDto getAcademicDepartmentById(Long id);
    boolean deleteAcademicDepartmentById(Long id);

    AcademicDepartmentDto updateAcademicDepartmentById(Long id, AcademicDepartmentUpdateDto academicDepartmentDto);
}
