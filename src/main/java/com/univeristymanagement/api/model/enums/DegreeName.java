package com.univeristymanagement.api.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.univeristymanagement.api.model.enums.DegreeAbbreviation.*;

@Getter
@AllArgsConstructor
public enum DegreeName {
    BACHELOR_OF_SCIENCE("Bachelor of Science"),
    BACHELOR_OF_ARTS("Bachelor of Arts"),
    MASTER_OF_SCIENCE("Master of Science"),
    MASTER_OF_ARTS("Master of Arts"),
    DOCTOR_OF_PHILOSOPHY("Doctor of Philosophy");

    private final String displayName;







}
