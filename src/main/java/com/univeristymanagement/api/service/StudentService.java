package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.StudentDto;
import com.univeristymanagement.api.model.Dto.StudentRegistrationDto;
import com.univeristymanagement.api.model.Dto.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    /**
     * Registers a student
     * @param studentDto
     * @return StudentDto
     */
    StudentDto registerStudent(StudentRegistrationDto studentDto);

    /**
     * Gets a student by id
     * @param id
     * @return StudentDto
     */
    StudentDto getStudentById(Long id);

    /**
     * Updates a student by id
     * @param id
     * @param studentDto
     * @return
     */
    StudentDto updateStudent(Long id, StudentUpdateDto studentDto);

    /**
     * Deletes a student by id
     * @param id
     */
    void deleteStudent(Long id);
    /**
     *  Checks if a user is already registered
     * @return List<StudentDto>
     */
    List<StudentDto> getAllStudents();

}
