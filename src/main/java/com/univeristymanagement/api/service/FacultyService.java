package com.univeristymanagement.api.service;


import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Dto.FacultyUpdateDto;
import com.univeristymanagement.api.model.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FacultyService {
    /**
     * Creates a faculty
     * @param facultyCreateDto
     * @return FacultyDto
     */
   FacultyDto createFaculty(FacultyCreateDto facultyCreateDto);

    /**
     * Gets all faculties
     * @param name
     * @param department
     * @param pageable
     * @return Page<FacultyDto>
     */
    Page<FacultyDto> getAllFaculties(String name, String department, Pageable pageable);
 /**
     * Gets a faculty by id
     * @param id
     * @return FacultyDto
     */

    FacultyDto getFacultyById(Long id);

    /**
     * Deletes a faculty by id
     * @param id
     * @return boolean
     */
    boolean deleteFacultyById(Long id);

    /**
     * Updates a faculty by id  and faculty object
     * @param id
     * @param faculty
     * @return FacultyDto
     */
    FacultyDto updateFaculty(Long id, FacultyUpdateDto faculty);

    /**
     * Checks if the id is valid
     * @param id
     * @return boolean
     */
    boolean isValidId(Long id);

    /**
     * Gets all academic departments by faculty id
     * @param id
     * @return List<AcademicDepartmentDto>
     */
    List<AcademicDepartmentDto> getAllAcademicDepartmentsByFacultyId(Long id);

    /**
     * Assigns an academic department to a faculty
     * @param facultyId
     * @param academicDepartmentId
     */
    void assignAcademicDepartmentToFaculty(Long facultyId, Long academicDepartmentId);

}
