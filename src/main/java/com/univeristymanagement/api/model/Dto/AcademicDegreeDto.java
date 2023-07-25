package com.univeristymanagement.api.model.Dto;

import com.univeristymanagement.api.model.enums.DegreeAbbreviation;
import com.univeristymanagement.api.model.enums.DegreeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicDegreeDto {

    private Long id;
    private DegreeName degreeName;
    private DegreeAbbreviation degreeAbbreviation;
}
