package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.Dto.StudentDto;
import com.univeristymanagement.api.model.Dto.StudentRegistrationDto;
import com.univeristymanagement.api.model.Dto.StudentUpdateDto;
import com.univeristymanagement.api.model.Student;

public class StudentMapper {

    public static Student studentRegistrationDtoToStudent(StudentRegistrationDto studentRegistrationDto) {
        Student student = new Student();
        student.setFirstName(studentRegistrationDto.getFirstName());
        student.setLastName(studentRegistrationDto.getLastName());
        student.setEmail(studentRegistrationDto.getEmail());
        student.setPassword(studentRegistrationDto.getPassword());
        student.setPreNominalTitles(studentRegistrationDto.getPreNominalTitles());
        student.setPostNominalTitles(studentRegistrationDto.getPostNominalTitles());
        student.setBirthday(studentRegistrationDto.getBirthday());
        return student;
    }

    public static StudentDto studentToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setPreNominalTitles(student.getPreNominalTitles());
        studentDto.setPostNominalTitles(student.getPostNominalTitles());
        studentDto.setBirthday(student.getBirthday());
        return studentDto;
    }



}
