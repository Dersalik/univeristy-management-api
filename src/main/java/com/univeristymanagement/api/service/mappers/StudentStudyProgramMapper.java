package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.Dto.StudentDto;
import com.univeristymanagement.api.model.Dto.StudentStudyProgramDto;
import com.univeristymanagement.api.model.Student;
import com.univeristymanagement.api.model.StudentStudyProgram;

public class StudentStudyProgramMapper {

    public static StudentStudyProgramDto studentToStudentDto(StudentStudyProgram dto){

        StudentStudyProgramDto studentStudyProgramDto = new StudentStudyProgramDto();
        studentStudyProgramDto.setId(dto.getId());
        studentStudyProgramDto.setEnrollmentDate(dto.getEnrollmentDate());
        studentStudyProgramDto.setGraduationDate(dto.getGraduationDate());
        studentStudyProgramDto.setActive(dto.isActive());
        studentStudyProgramDto.setStudent(StudentMapper.studentToDto(dto.getStudent()));
        studentStudyProgramDto.setStudyProgram(StudyProgramMapper.StudyProgramToDto(dto.getStudyProgram()));
        return studentStudyProgramDto;

    }
}
