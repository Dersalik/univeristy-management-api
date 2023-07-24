package com.univeristymanagement.api.model.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class AcademicRegistrationDTO {

    @NotNull(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    @Schema(description = "First name of the academic", example = "Karzan", required = true)
    private String firstName;

    @Schema(description = "Last name of the academic", example = "Karzan", required = true)
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    @NotNull(message = "Last name is required")
    private String lastName;

    @Schema(description = "Email of the academic")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Password of the academic")
    @NotNull(message = "Password is required")
    private String password;

    @Schema(description = "Pre nominal titles of the academic", example = "Dr.", required = true)
    @NotNull(message = "pre nominal titles is required")
    private String preNominalTitles;

    @Schema(description = "Post nominal titles of the academic", example = "Ph.D.", required = true)
    @NotNull(message = "post nominal titles is required")
    private String postNominalTitles;

    @Schema(description = "Academic department Id of the academic", example = "1", required = true)
    @NotNull(message = "Academic department Id is required")
    private Long departmentId;

}
