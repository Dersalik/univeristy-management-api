package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;

public class AcademicMapper {

    public static Academic academicRegstrationDtoToAcademic(AcademicRegistrationDTO academicDto, AcademicDepartment department ){


        Academic academic = new Academic();
        academic.setFirstName(academicDto.getFirstName());
        academic.setLastName(academicDto.getLastName());
        academic.setEmail(academicDto.getEmail());
        academic.setPassword(academicDto.getPassword());
        academic.setPostNominalTitles(academicDto.getPostNominalTitles());
        academic.setPreNominalTitles(academicDto.getPreNominalTitles());
        academic.setAcademicDepartment(department);

        return academic;

    }


    public static AcademicDto academicToDto(Academic academic){

        AcademicDto academicDto = new AcademicDto();
        academicDto.setId(academic.getId());
        academicDto.setFirstName(academic.getFirstName());
        academicDto.setLastName(academic.getLastName());
        academicDto.setEmail(academic.getEmail());
        academicDto.setPassword(academic.getPassword());
        academicDto.setPreNominalTitles(academic.getPreNominalTitles());
        academicDto.setPostNominalTitles(academic.getPostNominalTitles());
        academicDto.setAcademicDepartmentDto(
                AcademicDepartmentMapper.academicDepartmentToDto(academic.getAcademicDepartment()
                ,FacultyMapper.facultyToDto(academic.getAcademicDepartment().getFaculty())));

        return academicDto;
    }

}
