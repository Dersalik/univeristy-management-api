package com.univeristymanagement.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "study_program_degree")
@Getter
@Setter
public class StudyProgramDegree {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academic_degree_id")
    private AcademicDegree academicDegree;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;
}
