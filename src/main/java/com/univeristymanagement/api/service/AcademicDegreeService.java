package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDegreeCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicDegreeService {


//    AcademicDegreeDto addAcademicDegree(AcademicDegreeCreateDto academicDegreeDto);

    AcademicDegreeDto getAcademicDegreeById(Long id);

    List<AcademicDegreeDto> getAllAcademicDegrees();

//    AcademicDegreeDto deleteAcademicDegreeById(Long id);
}
