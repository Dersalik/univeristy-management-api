package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.*;
import com.univeristymanagement.api.model.StudyProgram;

import java.util.List;
import java.util.Set;

public interface StudyProgramService {
    /**
     * Gets all study programs
     * @param id
     * @param studyProgramUpdateDto
     * @return StudyProgramDto
     */
    StudyProgramDto updateStudyProgram(Long id,StudyProgramUpdateDto studyProgramUpdateDto);

    /**
     * Gets all study programs
     * @return List<StudyProgramDto>
     */
    List<StudyProgramDto> getAllStudyPrograms();

    /**
     * Gets a study program by id
     * @param id
     * @return StudyProgramDto
     */
    StudyProgramDto getStudyProgramById(Long id);

    /**
     * Deletes a study program by id
     * @param id
     * @return StudyProgramDto
     */

    StudyProgramDto deleteStudyProgramById(Long id);

    /**
     * Creates a study program
     * @param studyProgramDto
     * @return StudyProgramDto
     */
    StudyProgramDto createStudyProgram(StudyProgramCreateDto studyProgramDto);

    /**
     * GET all academic degrees by study program id
     * @param id
     * @return Set<AcademicDegreeDto>
     */
    Set<AcademicDegreeDto> getAcademicDegreesByStudyProgramId(Long id);

    /**
     * Adds an academic degree to a study program
     * @param academicDegreeId
     * @param studyProgramId
     */
    void addAcademicDegreeToStudyProgram(Long academicDegreeId, Long studyProgramId);

    /**
     * Deletes an academic degree from a study program
     * @param academicDegreeId
     * @param id
     */
    void deleteAcademicDegreeFromStudyProgram(Long academicDegreeId, Long id);

    /**
     * Adds a student to a study program
     * @param id
     * @param studentId
     * @param studentStudyProgramCreateDto
     */
    void addStudentToStudyProgram(long id,long studentId,StudyProgramCreateDto studentStudyProgramCreateDto);

    /**
     * Gets all Enrollments by study program id
     * @param id
     * @return Set<StudentStudyProgramDto>
     */
    Set<StudentStudyProgramDto> getEnrollmentsByStudyProgramId(Long id);

    /**
     * Gets an enrollment by id
     * @param id
     * @return StudentStudyProgramDto
     */
    StudentStudyProgramDto getEnrollmentById(Long id);
    /**
     * updates an enrollment by id
     * @param id
     * @return StudentStudyProgramDto
     */
    StudentStudyProgramDto updateEnrollment(Long id,StudentStudyProgramUpdateDto studentStudyProgramUpdateDto);

    /**
     * Deletes an enrollment by id
     * @param studyProgramId
     * @param studentId
     * @return void
     */
    void deleteEnrollmentById(Long studyProgramId, Long studentId);

}
