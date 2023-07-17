package com.univeristymanagement.api.model;


import com.univeristymanagement.api.model.enums.DegreeAbbreviation;
import com.univeristymanagement.api.model.enums.DegreeName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "academic_degree")
public class AcademicDegree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DegreeName degreeName;

    @Enumerated(EnumType.STRING)
    private DegreeAbbreviation degreeAbbreviation;

    @OneToMany(mappedBy = "academicDegree", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyProgramDegree> studyProgramDegrees;
}
