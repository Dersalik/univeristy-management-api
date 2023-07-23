package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.StudyProgramCreateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.model.Dto.StudyProgramUpdateDto;
import com.univeristymanagement.api.model.StudyProgram;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.repository.StudyProgramRepository;
import com.univeristymanagement.api.service.StudyProgramService;
import com.univeristymanagement.api.service.mappers.AcademicDepartmentMapper;
import com.univeristymanagement.api.service.mappers.StudyProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository studyProgramRepository;
    private final AcademicRepository academicRepository;
    private final AcademicDepartmentRepository academicDepartmentRepository;
    @Autowired
    public StudyProgramServiceImpl(StudyProgramRepository studyProgramRepository
            , AcademicRepository academicRepository
            , AcademicDepartmentRepository academicDepartmentRepository) {
        this.studyProgramRepository = studyProgramRepository;
        this.academicRepository = academicRepository;
        this.academicDepartmentRepository = academicDepartmentRepository;
    }

    /**
     * this method is responsible for updating a study program
     * @param id
     * @param studyProgramUpdateDto
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto updateStudyProgram(Long id, StudyProgramUpdateDto studyProgramUpdateDto) {
        Academic academic= academicRepository.findById(studyProgramUpdateDto.getAcademicId())
                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", studyProgramUpdateDto.getAcademicId()));

        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(studyProgramUpdateDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", studyProgramUpdateDto.getDepartmentId()));


        StudyProgram studyProgram = studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));

        studyProgram.setName(studyProgramUpdateDto.getName());
        studyProgram.setDescription(studyProgramUpdateDto.getDescription());
        studyProgram.setCapacity(studyProgramUpdateDto.getCapacity());
        studyProgram.setAcademic(academic);
        studyProgram.setAcademicDepartment(academicDepartment);

        studyProgramRepository.save(studyProgram);

        return StudyProgramMapper.StudyProgramToDto(studyProgram);



    }

    /**
     *  this method is responsible for getting all study programs
     * @return List<StudyProgramDto>
     */
    @Override
    public List<StudyProgramDto> getAllStudyPrograms() {
        return studyProgramRepository.findAll().stream()
                .map(StudyProgramMapper::StudyProgramToDto)
                .collect(Collectors.toList());
    }
    /**
     * this method is responsible for getting a study program by id
     * @param id
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto getStudyProgramById(Long id) {
        return studyProgramRepository.findById(id)
                .map(StudyProgramMapper::StudyProgramToDto)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));
    }

    /**
     * this method is responsible for deleting a study program by id
     * @param id
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto deleteStudyProgramById(Long id) {
        StudyProgram studyProgram = studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));

        studyProgramRepository.delete(studyProgram);

        return StudyProgramMapper.StudyProgramToDto(studyProgram);
    }

    /**
     *  this method is responsible for creating a new study program
     * @param studyProgramDto
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto createStudyProgram(StudyProgramCreateDto studyProgramDto) {

        Academic academic= academicRepository.findById(studyProgramDto.getAcademicId())
                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", studyProgramDto.getAcademicId()));

        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(studyProgramDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", studyProgramDto.getDepartmentId()));

        StudyProgram studyProgram = StudyProgramMapper.StudyProgramCreateDtoToStudyProgram(studyProgramDto
        ,academic,academicDepartment);

        studyProgramRepository.save(studyProgram);


        return StudyProgramMapper.StudyProgramToDto(studyProgram);
    }


}
