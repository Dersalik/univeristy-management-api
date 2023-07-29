package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDegreeCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicDegreeService {


//    AcademicDegreeDto addAcademicDegree(AcademicDegreeCreateDto academicDegreeDto);

    /**
     * this method is responsible for getting an academic degree by id
     * @param id the id of the academic degree
     * @return AcademicDegreeDto object
     */
    AcademicDegreeDto getAcademicDegreeById(Long id);

    /**
     * this method is responsible for getting all academic degrees
     * @return List<AcademicDegreeDto>
     */

    List<AcademicDegreeDto> getAllAcademicDegrees();

//    AcademicDegreeDto deleteAcademicDegreeById(Long id);
void academicDegreeExists(Long id);
}
