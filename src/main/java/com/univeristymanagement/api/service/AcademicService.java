package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.model.Dto.AcademicUpdateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;

import java.util.List;

public interface AcademicService {

    AcademicDto registerAcademic(AcademicRegistrationDTO academicDto);

    List<AcademicDto> getAllAcademics();
    AcademicDto getAcademicById(Long id);
    AcademicDto deleteAcademicById(Long id);

    AcademicDto updateAcademic(Long id, AcademicUpdateDto academicDto);

    void addStudyProgramToAcademic(Long studyProgramId, Long academicId);

    List<StudyProgramDto> getAllStudyProgramsByAcademicId(Long id);
}
