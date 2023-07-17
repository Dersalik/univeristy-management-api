package com.univeristymanagement.api.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DegreeAbbreviation {
    BS("BS"),
    BA("BA"),
    MS("MS"),
    MA("MA"),
    PhD("PhD");

    private final String abbreviation;


}
