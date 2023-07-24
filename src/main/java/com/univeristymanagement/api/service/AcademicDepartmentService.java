package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.*;

import java.util.List;

public interface AcademicDepartmentService {

    List<AcademicDepartmentDto> getAllAcademicDepartments();
    AcademicDepartmentDto createAcademicDepartment(AcademicDepartmentCreateDto academicDepartmentDto);
    AcademicDepartmentDto getAcademicDepartmentById(Long id);
    void deleteAcademicDepartmentById(Long id);

    AcademicDepartmentDto updateAcademicDepartmentById(Long id, AcademicDepartmentUpdateDto academicDepartmentDto);

    void addAcademicToAcademicDepartment(Long academicId, Long academicDepartmentId);

    void addStudyProgramToAcademicDepartment(Long studyProgramId, Long academicDepartmentId);

    List<AcademicDto> getAllAcademicsByAcademicDepartmentId(Long id);

    List<StudyProgramDto> getAllStudyProgramsByAcademicDepartmentId(Long id);
}
