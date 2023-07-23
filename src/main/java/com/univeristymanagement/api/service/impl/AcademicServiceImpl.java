package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.mappers.AcademicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public AcademicDto registerAcademic(AcademicRegistrationDTO academicDto) {

        checkIfDepartmentExistsById(academicDto.getDepartmentId());

        AcademicDepartment department= academicDepartmentRepository.findById(academicDto.getDepartmentId()).get();

        Academic academic = AcademicMapper.academicRegstrationDtoToAcademic(academicDto,department);

        academicRepository.save(academic);

        return AcademicMapper.academicToDto(academic);
    }

    private void checkIfDepartmentExistsById(Long id) {
        academicDepartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", id));
    }

}
