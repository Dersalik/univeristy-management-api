package com.univeristymanagement.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Academic class
 */
public class Academic extends Person {

    @ManyToOne
    @JoinColumn(name = "academic_department_id")
    private AcademicDepartment academicDepartment;

    @OneToMany(mappedBy = "academic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyProgram> studyPrograms;
}
