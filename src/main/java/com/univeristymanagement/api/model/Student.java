package com.univeristymanagement.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {

    @NotBlank(message = "Birthday is required")
//    @Past(message = "Birthday should be in the past")
//    @Min(value = 18, message = "Age should be at least 18 years")
//    @Max(value = 100, message = "Age should not be greater than 100 years")
    private LocalDate birthday;
}
