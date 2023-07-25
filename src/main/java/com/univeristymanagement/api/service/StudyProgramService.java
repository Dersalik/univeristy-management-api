package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;
import com.univeristymanagement.api.model.Dto.StudyProgramCreateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.model.Dto.StudyProgramUpdateDto;
import com.univeristymanagement.api.model.StudyProgram;

import java.util.List;
import java.util.Set;

public interface StudyProgramService {

    StudyProgramDto updateStudyProgram(Long id,StudyProgramUpdateDto studyProgramUpdateDto);
    List<StudyProgramDto> getAllStudyPrograms();
    StudyProgramDto getStudyProgramById(Long id);

    StudyProgramDto deleteStudyProgramById(Long id);

    StudyProgramDto createStudyProgram(StudyProgramCreateDto studyProgramDto);

    Set<AcademicDegreeDto> getAcademicDegreesByStudyProgramId(Long id);

    void addAcademicDegreeToStudyProgram(Long academicDegreeId, Long studyProgramId);

    void deleteAcademicDegreeFromStudyProgram(Long academicDegreeId, Long id);
}
