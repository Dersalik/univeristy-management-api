package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.AcademicDegree;
import com.univeristymanagement.api.model.Dto.AcademicDegreeCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;

public class AcademicDegreeMapper {


    public static AcademicDegree academicDegreeCreateDtoToAcademicDegree(AcademicDegreeCreateDto academicDegreeCreateDto){
        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setDegreeName(academicDegreeCreateDto.getDegreeName());
        academicDegree.setDegreeAbbreviation(academicDegreeCreateDto.getDegreeAbbreviation());
        return academicDegree;
    }


    public static AcademicDegreeDto academicDegreeToAcademicDegreeDto(AcademicDegree academicDegree){
        AcademicDegreeDto academicDegreeDto = new AcademicDegreeDto();
        academicDegreeDto.setId(academicDegree.getId());
        academicDegreeDto.setDegreeName(academicDegree.getDegreeName());
        academicDegreeDto.setDegreeAbbreviation(academicDegree.getDegreeAbbreviation());
        return academicDegreeDto;
    }
}
