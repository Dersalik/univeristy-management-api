package com.univeristymanagement.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "study_program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is required")
//    @Max(value = 70, message = "Name should not be greater than 70 characters")
    private String name;
    @NotNull(message = "Description is required")
//    @Max(value = 1000, message = "Description should not be greater than 200 characters")
    private String description;
    @NotNull(message = "Capacity is required")
//    @Max(value = 1000, message = "Capacity should not be greater than 200 characters")
//    @Min(value = 1, message = "Capacity should not be less than 1")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "academic_department_id")
    private AcademicDepartment academicDepartment;

    @ManyToOne
    @JoinColumn(name = "academic_id")
    private Academic academic;

    @OneToMany(mappedBy = "studyProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyProgramDegree> studyProgramDegrees;

    @OneToMany(mappedBy = "studyProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentStudyProgram> StudentStudyPrograms;




}
