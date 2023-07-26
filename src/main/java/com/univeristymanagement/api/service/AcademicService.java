package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.model.Dto.AcademicUpdateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;

import java.util.List;

public interface AcademicService {
    /**
     * Registers an academic
     * @param academicDto
     * @return AcademicDto
     */
    AcademicDto registerAcademic(AcademicRegistrationDTO academicDto);

    /**
     * Gets all academics
     * @return List<AcademicDto>
     */
    List<AcademicDto> getAllAcademics();

    /**
     *  Gets an academic by id
     * @param id
     * @return AcademicDto
     */
    AcademicDto getAcademicById(Long id);

    /**
     *  Deletes an academic by id
     * @param id
     * @return AcademicDto
     */
    AcademicDto deleteAcademicById(Long id);

    /**
     * Updates an academic by id
     * @param id
     * @param academicDto
     * @return AcademicDto
     */
    AcademicDto updateAcademic(Long id, AcademicUpdateDto academicDto);

    /**
     * Adds an academic degree to an academic
     * @param studyProgramId
     * @param academicId
     */
    void addStudyProgramToAcademic(Long studyProgramId, Long academicId);

    /**
     * Gets all study programs by academic id
     * @param id
     * @return List<StudyProgramDto>
     */
    List<StudyProgramDto> getAllStudyProgramsByAcademicId(Long id);
}
