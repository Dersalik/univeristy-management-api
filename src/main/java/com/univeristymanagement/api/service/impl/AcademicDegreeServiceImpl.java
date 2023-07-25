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
    private StudyProgramDegreeRepository studyProgramDegreeRepository;


    @Autowired
    public AcademicDegreeServiceImpl(AcademicDegreeRepository academicDegreeRepository, StudyProgramDegreeRepository studyProgramDegreeRepository){
        this.academicDegreeRepository = academicDegreeRepository;
        this.studyProgramDegreeRepository = studyProgramDegreeRepository;
    }


//    @Override
//    public AcademicDegreeDto addAcademicDegree(AcademicDegreeCreateDto academicDegreeDto) {
//
//        if(!AcademicDegree.validatedIfDegreeNameAndAbbreviationAreEqual(academicDegreeDto.getDegreeName(),academicDegreeDto.getDegreeAbbreviation())){
//             throw new ApiException("Degree name and Abbreviation are not equal");
//        }
//
//
//
//        AcademicDegree academicDegree = AcademicDegreeMapper.academicDegreeCreateDtoToAcademicDegree(academicDegreeDto);
//       AcademicDegree savedAcademicDegree = academicDegreeRepository.save(academicDegree);
//
//        return AcademicDegreeMapper.academicDegreeToAcademicDegreeDto(savedAcademicDegree);
//    }

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

//    @Override
//    public AcademicDegreeDto deleteAcademicDegreeById(Long id) {
//        academicDegreeExists(id);
//        AcademicDegree academicDegree = academicDegreeRepository.findById(id).get();
//        academicDegreeRepository.deleteById(id);
//
//        return AcademicDegreeMapper.academicDegreeToAcademicDegreeDto(academicDegree);
//    }

    /**
     * Checks if an academic degree exists by id
     * @param id
     */
    private void academicDegreeExists(Long id){
        if(!academicDegreeRepository.existsById(id)){
            throw new ResourceNotFoundException("Academic Degree","id", id);
        }
    }

}
