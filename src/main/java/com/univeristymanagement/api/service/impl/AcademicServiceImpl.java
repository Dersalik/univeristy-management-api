package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.model.Dto.AcademicUpdateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.model.StudyProgram;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.repository.StudyProgramRepository;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.mappers.AcademicMapper;
import com.univeristymanagement.api.service.mappers.StudyProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
/**
 * This class is responsible for implementing the AcademicService interface
 */
public class AcademicServiceImpl implements AcademicService {

    private AcademicRepository academicRepository;
    private AcademicDepartmentRepository academicDepartmentRepository;

    private StudyProgramRepository studyProgramRepository;

    @Autowired
    public AcademicServiceImpl(AcademicRepository academicRepository
            , AcademicDepartmentRepository academicDepartmentRepository
            , StudyProgramRepository studyProgramRepository)
    {
        this.studyProgramRepository=studyProgramRepository;
        this.academicRepository=academicRepository;
        this.academicDepartmentRepository=academicDepartmentRepository;
    }
    /**
     * Checks if an academic exists by id
     * @param academicDto
     * @return boolean
     */
    @Override
    public AcademicDto registerAcademic(AcademicRegistrationDTO academicDto) {

        checkIfDepartmentExistsById(academicDto.getDepartmentId());

        AcademicDepartment department= academicDepartmentRepository.findById(academicDto.getDepartmentId()).get();

        Academic academic = AcademicMapper.academicRegstrationDtoToAcademic(academicDto,department);

        academicRepository.save(academic);

        return AcademicMapper.academicToDto(academic);
    }
    /**
     * Gets all academics
     * @return List<AcademicDto>
     */
    @Override
    public List<AcademicDto> getAllAcademics() {
        return academicRepository.findAll().stream().map(academic -> {
            AcademicDto academicDto = AcademicMapper.academicToDto(academic);
            return academicDto;
        }).collect(java.util.stream.Collectors.toList());
    }

    /**
    * Gets an academic by id
    * @param id
    * @return AcademicDto
     */
    @Override
    public AcademicDto getAcademicById(Long id) {
        checkIfAcademicExistsById(id);
        return AcademicMapper.academicToDto(academicRepository.findById(id).get());
    }

    /**
     * Deletes an academic by id
     * @param id
     * @return AcademicDto
     */
    @Override
    public AcademicDto deleteAcademicById(Long id) {
        checkIfAcademicExistsById(id);
        Academic academic = academicRepository.findById(id).get();
        academicRepository.deleteById(id);
        return AcademicMapper.academicToDto(academic);
    }

    /**
     * Updates an academic by id
     * @param id
     * @param academicDto
     * @return AcademicDto
     */
    @Override
    public AcademicDto updateAcademic(Long id, AcademicUpdateDto academicDto) {
        checkIfAcademicExistsById(id);
        checkIfDepartmentExistsById(academicDto.getDepartmentId());


        Academic academic = academicRepository.findById(id).get();
        AcademicDepartment department= academicDepartmentRepository.findById(academicDto.getDepartmentId()).get();
        academic.setAcademicDepartment(department);
        academic.setFirstName(academicDto.getFirstName());
        academic.setLastName(academicDto.getLastName());
        academic.setPreNominalTitles(academicDto.getPreNominalTitles());
        academic.setPostNominalTitles(academicDto.getPostNominalTitles());


        academicRepository.save(academic);
        return AcademicMapper.academicToDto(academic);
    }

    /**
     * Adds a study program to an academic
     * @param studyProgramId
     * @param academicId
     * @return void
     */
    @Override
    public void addStudyProgramToAcademic(Long studyProgramId, Long academicId) {
        checkIfStudyProgramStudyExistsById(studyProgramId);
        checkIfAcademicExistsById(academicId);

        StudyProgram studyProgram = studyProgramRepository.findById(studyProgramId).get();
        Academic academic = academicRepository.findById(academicId).get();

        studyProgram.setAcademic(academic);


        studyProgramRepository.save(studyProgram);
    }


    /**
     * Gets all study programs by academic id
     * @param id
     * @return List<StudyProgramDto>
     */
    @Override
    public List<StudyProgramDto> getAllStudyProgramsByAcademicId(Long id) {
        checkIfAcademicExistsById(id);
        return studyProgramRepository.findAllByAcademicId(id).get().stream().map(studyProgram -> {
            StudyProgramDto studyProgramDto = StudyProgramMapper.StudyProgramToDto(studyProgram);
            return studyProgramDto;
        }).collect(Collectors.toList());
    }

    /**
     * Checks if a department exists by id
     * @param id
     */
    private void checkIfDepartmentExistsById(Long id) {
        academicDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", id));
    }
    /**
     * Checks if an academic exists by id
     * @param id
     */
    private void checkIfAcademicExistsById(Long id){
        academicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", id));
    }

    /**
     * check if Study program exists by id
     * @param id
     * @return void
     */
    private void checkIfStudyProgramStudyExistsById(long id){
        studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));
    }

}
