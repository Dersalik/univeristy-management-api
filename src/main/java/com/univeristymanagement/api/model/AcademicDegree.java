package com.univeristymanagement.api.model;


import com.univeristymanagement.api.model.enums.DegreeAbbreviation;
import com.univeristymanagement.api.model.enums.DegreeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "academic_degree")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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



//    public static boolean validatedIfDegreeNameAndAbbreviationAreEqual(DegreeName degreeName, DegreeAbbreviation degreeAbbreviation) {
//        if (degreeName == null || degreeAbbreviation == null) {
//            return false;
//        }
//
//        switch (degreeName) {
//            case BACHELOR_OF_SCIENCE:
//                return degreeAbbreviation.getAbbreviation().equals("BS");
//            case BACHELOR_OF_ARTS:
//                return degreeAbbreviation.getAbbreviation().equals("BA");
//            case MASTER_OF_SCIENCE:
//                return degreeAbbreviation.getAbbreviation().equals("MS");
//            case MASTER_OF_ARTS:
//                return degreeAbbreviation.getAbbreviation().equals("MA");
//            case DOCTOR_OF_PHILOSOPHY:
//                return degreeAbbreviation.getAbbreviation().equals("PhD");
//            default:
//                return false; // Handle other cases if needed
//        }
//    }
}
