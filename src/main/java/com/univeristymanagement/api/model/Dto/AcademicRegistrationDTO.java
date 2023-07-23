package com.univeristymanagement.api.model.Dto;


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
    private String firstName;
    @Size(min = 2, max = 50, message = "Middle name should be between 2 and 50 characters")
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "pre nominal titles is required")
    private String preNominalTitles;
    @NotNull(message = "post nominal titles is required")
    private String postNominalTitles;
    @NotNull(message = "Academic department Id is required")
    private Long departmentId;

}
