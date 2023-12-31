package com.univeristymanagement.api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    private String firstName;
    @Size(min = 2, max = 50, message = "Middle name should be between 2 and 50 characters")
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "pre nominal titles is required")
    private String preNominalTitles;
    @NotNull(message = "post nominal titles is required")
    private String postNominalTitles;


}
