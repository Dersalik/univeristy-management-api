package com.univeristymanagement.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class StudentStudyProgram {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate EnrollmentDate;
    private LocalDate GraduationDate;
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;



}
