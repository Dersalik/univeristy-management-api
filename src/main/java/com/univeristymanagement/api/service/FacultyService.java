package com.univeristymanagement.api.service;


import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Dto.FacultyUpdateDto;
import com.univeristymanagement.api.model.Faculty;

import java.util.List;

public interface FacultyService {

   FacultyDto createFaculty(FacultyCreateDto facultyCreateDto);

   List<FacultyDto> getAllFaculties();

    FacultyDto getFacultyById(Long id);

    boolean deleteFacultyById(Long id);

    FacultyDto updateFaculty(Long id, FacultyUpdateDto faculty);

    boolean isValidId(Long id);

    List<AcademicDepartmentDto> getAllAcademicDepartmentsByFacultyId(Long id);

}
