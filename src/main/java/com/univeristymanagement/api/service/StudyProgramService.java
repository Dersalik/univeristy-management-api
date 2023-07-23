package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.StudyProgramCreateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.model.Dto.StudyProgramUpdateDto;
import com.univeristymanagement.api.model.StudyProgram;

import java.util.List;

public interface StudyProgramService {

    StudyProgramDto updateStudyProgram(Long id,StudyProgramUpdateDto studyProgramUpdateDto);
    List<StudyProgramDto> getAllStudyPrograms();
    StudyProgramDto getStudyProgramById(Long id);

    StudyProgramDto deleteStudyProgramById(Long id);

    StudyProgramDto createStudyProgram(StudyProgramCreateDto studyProgramDto);
}
