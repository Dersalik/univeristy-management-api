package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentUpdateDto;
import com.univeristymanagement.api.model.Faculty;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.FacultyRepository;
import com.univeristymanagement.api.service.AcademicDepartmentService;
import com.univeristymanagement.api.service.mappers.AcademicDepartmentMapper;
import com.univeristymanagement.api.service.mappers.FacultyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AcademicDepartmentServiceImp implements AcademicDepartmentService {

    private AcademicDepartmentRepository academicDepartmentRepository;

    private FacultyRepository facultyRepository;
    @Autowired
    public AcademicDepartmentServiceImp(AcademicDepartmentRepository academicDepartmentRepository, FacultyRepository facultyRepository){
        this.academicDepartmentRepository = academicDepartmentRepository;
        this.facultyRepository = facultyRepository;
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
     * @return boolean
     */
    @Override
    public boolean deleteAcademicDepartmentById(Long id) {
        checkIfDepartmentExistsById(id);

        academicDepartmentRepository.deleteById(id);
        return true;
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

}
