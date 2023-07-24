package com.univeristymanagement.api.model.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicUpdateDto {
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    @Schema(description = "First name of the academic", example = "Karzan", required = true)
    private String firstName;

    @Schema(description = "Last name of the academic", example = "Saman", required = true)
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    @NotNull(message = "Last name is required")
    private String lastName;

    @Schema(description = "Pre-nominal titles of the academic", example = "Dr.", required = true)
    @NotNull(message = "Pre-nominal titles is required")
    private String preNominalTitles;

    @Schema(description = "Post-nominal titles of the academic", example = "Ph.D.", required = true)
    @NotNull(message = "Post-nominal titles is required")
    private String postNominalTitles;

    @Schema(description = "Academic department Id of the academic", example = "1", required = true)
    @NotNull(message = "Academic department Id is required")
    private Long departmentId;
}
