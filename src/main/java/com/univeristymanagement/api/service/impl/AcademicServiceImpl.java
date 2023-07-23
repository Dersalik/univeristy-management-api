package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.model.Dto.AcademicUpdateDto;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.mappers.AcademicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicServiceImpl implements AcademicService {

    private AcademicRepository academicRepository;
    private AcademicDepartmentRepository academicDepartmentRepository;

    @Autowired
    public AcademicServiceImpl(AcademicRepository academicRepository, AcademicDepartmentRepository academicDepartmentRepository)
    {

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
     * Checks if a department exists by id
     * @param id
     */
    private void checkIfDepartmentExistsById(Long id) {
        academicDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", id));
    }
    /**
     * Checks if an academic exists by id
     * @param Id
     */
    private void checkIfAcademicExistsById(Long Id){
        academicRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", Id));
    }

}
