package com.univeristymanagement.api.model.Dto;

import com.univeristymanagement.api.model.Student;
import com.univeristymanagement.api.model.StudyProgram;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentStudyProgramDto {
    private Long id;
    private LocalDate EnrollmentDate;
    private LocalDate GraduationDate;
    private boolean isActive;
    private StudentDto student;
    private StudyProgramDto studyProgram;


}
