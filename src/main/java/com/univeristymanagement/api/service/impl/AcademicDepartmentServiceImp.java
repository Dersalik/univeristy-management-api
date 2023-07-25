package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.*;
import com.univeristymanagement.api.model.Faculty;
import com.univeristymanagement.api.model.StudyProgram;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.repository.FacultyRepository;
import com.univeristymanagement.api.repository.StudyProgramRepository;
import com.univeristymanagement.api.service.AcademicDepartmentService;
import com.univeristymanagement.api.service.mappers.AcademicDepartmentMapper;
import com.univeristymanagement.api.service.mappers.AcademicMapper;
import com.univeristymanagement.api.service.mappers.FacultyMapper;
import com.univeristymanagement.api.service.mappers.StudyProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
/**
 * This class is responsible for implementing the AcademicDepartmentService interface
 */
public class AcademicDepartmentServiceImp implements AcademicDepartmentService {

    private AcademicDepartmentRepository academicDepartmentRepository;

    private FacultyRepository facultyRepository;

    private AcademicRepository academicRepository;

    private StudyProgramRepository studyProgramRepository;
    @Autowired
    public AcademicDepartmentServiceImp(AcademicDepartmentRepository academicDepartmentRepository
            , FacultyRepository facultyRepository
            , AcademicRepository academicRepository
            , StudyProgramRepository studyProgramRepository){
        this.academicDepartmentRepository = academicDepartmentRepository;
        this.facultyRepository = facultyRepository;
        this.academicRepository = academicRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    /**
     * Gets all academic departments
     * @return List<AcademicDepartmentDto>
     */
    @Override
    public List<AcademicDepartmentDto> getAllAcademicDepartments() {
        return academicDepartmentRepository.findAll().stream().map(academicDepartment -> {
            AcademicDepartmentDto academicDepartmentDto = AcademicDepartmentMapper.academicDepartmentToDto(academicDepartment
            , FacultyMapper.facultyToDto(academicDepartment.getFaculty()));
            return academicDepartmentDto;
        }).collect(Collectors.toList());
    }

    /**
     * Creates an academic department
     * @param academicDepartmentDto
     * @return AcademicDepartmentDto
     */
    @Override
    public AcademicDepartmentDto createAcademicDepartment(AcademicDepartmentCreateDto academicDepartmentDto) {

        checkIfFacultyExistsById(academicDepartmentDto.getFacultyId());
        Faculty faculty = facultyRepository.findById(academicDepartmentDto.getFacultyId()).get();


        AcademicDepartment academicDepartment = AcademicDepartmentMapper.academicDepartmentCreateDtoToAcademicDepartment(academicDepartmentDto);
        academicDepartment.setFaculty(faculty);

        academicDepartmentRepository.save(academicDepartment);

        return AcademicDepartmentMapper.academicDepartmentToDto(academicDepartment,
                FacultyMapper.facultyToDto(academicDepartment.getFaculty()));


    }

    /**
     *  Gets an academic department by id
     * @param id
     * @return AcademicDepartmentDto
     */
    @Override
    public AcademicDepartmentDto getAcademicDepartmentById(Long id) {
        checkIfDepartmentExistsById(id);

        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(id).get();

        AcademicDepartmentDto academicDepartmentDto = AcademicDepartmentMapper.academicDepartmentToDto(academicDepartment
        , FacultyMapper.facultyToDto(academicDepartment.getFaculty()));

        return academicDepartmentDto;
    }
    /**
     * Deletes an academic department by id
     * @param id
     * @return void
     */
    @Override
    public void deleteAcademicDepartmentById(Long id) {
        checkIfDepartmentExistsById(id);

        academicDepartmentRepository.deleteById(id);
    }

    /**
     *  Updates an academic department by id
     * @param id
     * @param academicDepartmentDto
     * @return AcademicDepartmentDto
     */
    @Override
    public AcademicDepartmentDto  updateAcademicDepartmentById(Long id, AcademicDepartmentUpdateDto academicDepartmentDto)
    {
        checkIfDepartmentExistsById(id);

        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(id).get();

        academicDepartment.setName(academicDepartmentDto.getName());

        academicDepartmentRepository.save(academicDepartment);


        AcademicDepartmentDto academicDepartmentDtoToReturn = AcademicDepartmentMapper.academicDepartmentToDto(academicDepartment
        , FacultyMapper.facultyToDto(academicDepartment.getFaculty()));


        return academicDepartmentDtoToReturn ;
    }

    /**
     *  Adds an academic to an academic department
     * @param academicId
     * @param academicDepartmentId
     */
    @Override
    public void addAcademicToAcademicDepartment(Long academicId, Long academicDepartmentId) {
        checkIfDepartmentExistsById(academicDepartmentId);
        checkIfAcademicExistsById(academicId);


        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(academicDepartmentId).get();

        Academic academic = academicRepository.findById(academicId).get();

        academic.setAcademicDepartment(academicDepartment);

        academicRepository.save(academic);


    }

    /**
     *  Gets all academics by academic department id
     * @param id
     * @return List<AcademicDto>
     */
    @Override
    public List<AcademicDto> getAllAcademicsByAcademicDepartmentId(Long id) {
        checkIfDepartmentExistsById(id);

        AcademicDepartment academicDepartment = academicDepartmentRepository.findAcademicDepartmentById(id).get();
        List<Academic> academics = academicDepartment.getAcademics();

        List<AcademicDto> academicDtos = academics.stream().map(academic -> {
            AcademicDto academicDto = AcademicMapper.academicToDto(academic);

            return academicDto;
        }).collect(Collectors.toList());

        return academicDtos;
    }

    /**
     *  Adds a study program to an academic department
     * @param studyProgramId
     * @param academicDepartmentId
     */
    @Override
    public void addStudyProgramToAcademicDepartment(Long studyProgramId, Long academicDepartmentId) {
        checkIfDepartmentExistsById(academicDepartmentId);
        checkIfStudyProgramExistsById(studyProgramId);


        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(academicDepartmentId).get();
        StudyProgram studyProgram = studyProgramRepository.findById(studyProgramId).get();

        studyProgram.setAcademicDepartment(academicDepartment);

        studyProgramRepository.save(studyProgram);

    }

    /**
     * Gets all study programs by academic department id
     * @param id
     * @return List<StudyProgramDto>
     */
    @Override
    public List<StudyProgramDto> getAllStudyProgramsByAcademicDepartmentId(Long id){
        checkIfDepartmentExistsById(id);

        List<StudyProgram> studyPrograms=studyProgramRepository.findAllByAcademicDepartmentId(id).get();

        List<StudyProgramDto> studyProgramDtos=studyPrograms.stream().map(studyProgram -> {
            return StudyProgramMapper.StudyProgramToDto(studyProgram);
        }).collect(Collectors.toList());

        return studyProgramDtos;
    }


    /**
     *  Checks if an academic department exists by id
     * @param id
     */
    private void checkIfDepartmentExistsById(Long id) {
        academicDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", id));
    }
    /**
     *  Checks if a faculty exists by id
     * @param id
     */
    private void checkIfFacultyExistsById(Long id) {
        facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", id));
    }

    /**
     *  Checks if an academic exists by id
     * @param id
     */
    private void checkIfAcademicExistsById(Long id) {
        academicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", id));
        }


    /**
     * Checks if a study program exists by id
     * @param id
     * @return void
     */
    private void checkIfStudyProgramExistsById(Long id) {
        studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));
    }
}
