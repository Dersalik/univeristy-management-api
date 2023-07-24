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
    private String name;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be greater than 0")
    @Max(value = 100, message = "Capacity must be less than 100")
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_department_id")
    private AcademicDepartment academicDepartment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_id")
    private Academic academic;

    @OneToMany(mappedBy = "studyProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyProgramDegree> studyProgramDegrees;

    @OneToMany(mappedBy = "studyProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentStudyProgram> StudentStudyPrograms;




}
