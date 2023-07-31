package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ApiException;
import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.AcademicDegree;
import com.univeristymanagement.api.model.Dto.AcademicDegreeCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;
import com.univeristymanagement.api.model.enums.DegreeName;
import com.univeristymanagement.api.repository.AcademicDegreeRepository;
import com.univeristymanagement.api.repository.StudyProgramDegreeRepository;
import com.univeristymanagement.api.service.AcademicDegreeService;
import com.univeristymanagement.api.service.mappers.AcademicDegreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * This class is responsible for implementing the AcademicDegreeService interface
 */
public class AcademicDegreeServiceImpl implements AcademicDegreeService {

    private AcademicDegreeRepository academicDegreeRepository;
//    private StudyProgramDegreeRepository studyProgramDegreeRepository;


    @Autowired
    public AcademicDegreeServiceImpl(AcademicDegreeRepository academicDegreeRepository, StudyProgramDegreeRepository studyProgramDegreeRepository){
        this.academicDegreeRepository = academicDegreeRepository;
//        this.studyProgramDegreeRepository = studyProgramDegreeRepository;
    }

    /**
     *  Gets an academic degree by id
     * @param id
     * @return AcademicDegreeDto
     */
    @Override
    public AcademicDegreeDto getAcademicDegreeById(Long id) {
        academicDegreeExists(id);


        return  academicDegreeRepository
                .findById(id).map(AcademicDegreeMapper::academicDegreeToAcademicDegreeDto).get();

    }

    /**
     * Gets all academic degrees
     * @return List<AcademigDegreeDto>
     */
    @Override
    public List<AcademicDegreeDto> getAllAcademicDegrees() {
        return academicDegreeRepository
                .findAll()
                .stream()
                .map(AcademicDegreeMapper::academicDegreeToAcademicDegreeDto)
                .toList();
    }


    /**
     * Checks if an academic degree exists by id
     * @param id
     */
    @Override
    public void academicDegreeExists(Long id){
        if(!academicDegreeRepository.existsById(id)){
            throw new ResourceNotFoundException("Academic Degree","id", id);
        }
    }

}
