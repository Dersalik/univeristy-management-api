package com.univeristymanagement.api.model.Dto;

import com.univeristymanagement.api.model.enums.DegreeAbbreviation;
import com.univeristymanagement.api.model.enums.DegreeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicDegreeCreateDto {

    @Schema(description = "Degree name of the academic degree")
    @NotNull(message = "Degree name is required")
    private DegreeName degreeName;

    @NotNull(message = "Degree abbreviation is required")
    private DegreeAbbreviation degreeAbbreviation;
}
