package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.StudyProgramCreateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.model.Dto.StudyProgramUpdateDto;
import com.univeristymanagement.api.model.StudyProgram;

public class StudyProgramMapper {

    public static StudyProgramDto StudyProgramToDto(StudyProgram studyProgramDto){
        StudyProgramDto studyProgram = new StudyProgramDto();
        studyProgram.setId(studyProgramDto.getId());
        studyProgram.setName(studyProgramDto.getName());
        studyProgram.setDescription(studyProgramDto.getDescription());
        studyProgram.setCapacity(studyProgramDto.getCapacity());
        studyProgram.setAcademicDto(AcademicMapper.academicToDto(studyProgramDto.getAcademic()));
        studyProgram.setAcademicDepartmentDto(AcademicDepartmentMapper.academicDepartmentToDto(studyProgramDto.getAcademicDepartment()
                , FacultyMapper.facultyToDto(studyProgramDto.getAcademicDepartment().getFaculty())));

        return studyProgram;
    }


    public static StudyProgram StudyProgramCreateDtoToStudyProgram(StudyProgramCreateDto studyProgramDto
    , Academic academic, AcademicDepartment academicDepartment
    ){
        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName(studyProgramDto.getName());
        studyProgram.setDescription(studyProgramDto.getDescription());
        studyProgram.setCapacity(studyProgramDto.getCapacity());
        studyProgram.setAcademic(academic);
        studyProgram.setAcademicDepartment(academicDepartment);

        return studyProgram;
    }


    public static StudyProgram StudyProgramUpdateDtoToStudyProgram(StudyProgramUpdateDto studyProgramDto
            , Academic academic, AcademicDepartment academicDepartment
    ){
        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName(studyProgramDto.getName());
        studyProgram.setDescription(studyProgramDto.getDescription());
        studyProgram.setCapacity(studyProgramDto.getCapacity());
        studyProgram.setAcademic(academic);
        studyProgram.setAcademicDepartment(academicDepartment);


        return studyProgram;
    }


}
