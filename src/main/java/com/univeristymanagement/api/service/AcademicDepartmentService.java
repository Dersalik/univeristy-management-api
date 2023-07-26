package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.*;

import java.util.List;

public interface AcademicDepartmentService {

    /**
     * Gets all academic departments
     * @return List<AcademicDepartmentDto>
     */
    List<AcademicDepartmentDto> getAllAcademicDepartments();

    /**
     * Creates an academic department
     * @param academicDepartmentDto
     * @return AcademicDepartmentDto
     */
    AcademicDepartmentDto createAcademicDepartment(AcademicDepartmentCreateDto academicDepartmentDto);
    /**
     * Gets an academic department by id
     * @param id
     * @return AcademicDepartmentDto
     */
    AcademicDepartmentDto getAcademicDepartmentById(Long id);
    /**
     * Deletes an academic department by id
     * @param id
     */
    void deleteAcademicDepartmentById(Long id);

    /**
     * Updates an academic department by id
     * @param id
     * @param academicDepartmentDto
     * @return
     */
    AcademicDepartmentDto updateAcademicDepartmentById(Long id, AcademicDepartmentUpdateDto academicDepartmentDto);

    /**
     * Adds an academic to an academic department
     * @param academicId
     * @param academicDepartmentId
     */
    void addAcademicToAcademicDepartment(Long academicId, Long academicDepartmentId);

    /**
     * Adds a study program to an academic department
     * @param studyProgramId
     * @param academicDepartmentId
     */
    void addStudyProgramToAcademicDepartment(Long studyProgramId, Long academicDepartmentId);

    /**
     * Gets all academics by academic department id
     * @param id
     * @return List<AcademicDto>
     */
    List<AcademicDto> getAllAcademicsByAcademicDepartmentId(Long id);

    /**
     * Gets all study programs by academic department id
     * @param id
     * @return List<StudyProgramDto>
     */
    List<StudyProgramDto> getAllStudyProgramsByAcademicDepartmentId(Long id);
}
