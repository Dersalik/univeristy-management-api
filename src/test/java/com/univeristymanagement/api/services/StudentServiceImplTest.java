package com.univeristymanagement.api.services;

import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.exceptions.UserAlreadyRegisteredException;
import com.univeristymanagement.api.model.Dto.StudentDto;
import com.univeristymanagement.api.model.Dto.StudentRegistrationDto;
import com.univeristymanagement.api.model.Dto.StudentUpdateDto;
import com.univeristymanagement.api.model.Student;
import com.univeristymanagement.api.repository.AcademicRepository;
import com.univeristymanagement.api.repository.StudentRepository;
import com.univeristymanagement.api.service.impl.StudentServiceImpl;
import com.univeristymanagement.api.service.mappers.StudentMapper;
import jakarta.validation.constraints.Email;
import org.assertj.core.api.DateAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private AcademicRepository academicRepository;
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;
    StudentRegistrationDto studentRegistrationDto = new StudentRegistrationDto(
            "hama",
            "hama",
            "hama@Gmail.com",
            "123456789",
            "test",
            "test",
             LocalDate.now()
    );

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void RegisterStudent_emailAlreadyExists_throwsException() {
        // Arrange

        when(studentRepository.existsByEmail(studentRegistrationDto.getEmail())).thenReturn(true);

        //act and assert
        assertThrows(UserAlreadyRegisteredException.class, () -> {
            studentServiceImpl.registerStudent(studentRegistrationDto);
        });
    }


    @Test
    public void RegisterStudent_validEmail_returnsStudentDto() {
        // Arrange
        Student student = StudentMapper.studentRegistrationDtoToStudent(studentRegistrationDto);
        student.setId(1L);
        when(studentRepository.existsByEmail(studentRegistrationDto.getEmail())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        studentServiceImpl.registerStudent(studentRegistrationDto);

        // Assert
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void getStudentById_InvalidId_shouldThrowException(){
        // Arrange
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            studentServiceImpl.getStudentById(studentId);
        });
    }


    @Test
    public void getStudentById_ValidId_shouldReturnDto(){
         //arrange
        Long studentId = 1L;
        Student student = StudentMapper.studentRegistrationDtoToStudent(studentRegistrationDto);
        student.setId(1L);
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        //act
        studentServiceImpl.getStudentById(studentId);

        //assert
        verify(studentRepository, times(2)).findById(any(Long.class) );
        assertDoesNotThrow(() -> {
            studentServiceImpl.getStudentById(studentId);
        });
    }


    @Test
    public void updateStudent_validId_shouldReturnDto(){
        //arrange
        Long studentId = 1L;
        Student student = StudentMapper.studentRegistrationDtoToStudent(studentRegistrationDto);
        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();

        studentUpdateDto.setFirstName("test");
        studentUpdateDto.setLastName("test");
        studentUpdateDto.setPreNominalTitles("test");
        studentUpdateDto.setPostNominalTitles("test");
        studentUpdateDto.setBirthday(LocalDate.now());

        student.setId(1L);
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        //act
        StudentDto studentDto=studentServiceImpl.updateStudent(studentId, studentUpdateDto);

        //assert
        verify(studentRepository, times(1)).save(any(Student.class));
        assertEquals("should be equal",studentDto.getFirstName(),studentUpdateDto.getFirstName());
        assertEquals("should be equal",studentDto.getLastName(),studentUpdateDto.getLastName());
        assertEquals("should be equal",studentDto.getPreNominalTitles(),studentUpdateDto.getPreNominalTitles());
        assertEquals("should be equal",studentDto.getPostNominalTitles(),studentUpdateDto.getPostNominalTitles());
        assertEquals("should be equal",studentDto.getBirthday(),studentUpdateDto.getBirthday());






    }


}
