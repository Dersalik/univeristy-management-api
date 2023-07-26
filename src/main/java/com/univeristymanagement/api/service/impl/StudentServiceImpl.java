package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ApiException;
import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.exceptions.UserAlreadyRegisteredException;
import com.univeristymanagement.api.model.Dto.StudentDto;
import com.univeristymanagement.api.model.Dto.StudentRegistrationDto;
import com.univeristymanagement.api.model.Dto.StudentUpdateDto;
import com.univeristymanagement.api.model.Student;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.repository.StudentRepository;
import com.univeristymanagement.api.service.StudentService;
import com.univeristymanagement.api.service.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AcademicRepository academicRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, AcademicRepository academicRepository){
        this.studentRepository = studentRepository;
        this.academicRepository = academicRepository;
    }

    @Override
    public StudentDto registerStudent(StudentRegistrationDto studentDto) {
        if(checkIfUserAlreadyRegistered(studentDto.getEmail())){
            throw new UserAlreadyRegisteredException(studentDto.getEmail());
        }
       Student student= studentRepository.save(StudentMapper.studentRegistrationDtoToStudent(studentDto));

        return StudentMapper.studentToDto(student);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        userExistsById(id);

        Student student = studentRepository.findById(id).get();
        return StudentMapper.studentToDto(student);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentUpdateDto studentDto) {

        userExistsById(id);

        Student student = studentRepository.findById(id).get();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPreNominalTitles(studentDto.getPreNominalTitles());
        student.setPostNominalTitles(studentDto.getPostNominalTitles());
        student.setBirthday(studentDto.getBirthday());
        studentRepository.save(student);


        return StudentMapper.studentToDto(student);
    }

    @Override
    public void deleteStudent(Long id) {
      userExistsById(id);

      studentRepository.deleteById(id);


    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(StudentMapper::studentToDto).toList();
    }

    private boolean checkIfUserAlreadyRegistered(String email){
        boolean isRegistered = false;

        if(studentRepository.existsByEmail(email) || academicRepository.existsByEmail(email)){
            isRegistered = true;
        }
        return isRegistered;
    }

    /**
     * Checks if a department exists by id
     * @param id
     */
    private void userExistsById(Long id){
         studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

    }


}
