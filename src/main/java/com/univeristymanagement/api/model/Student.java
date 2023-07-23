package com.univeristymanagement.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {

    @NotNull(message = "Birthday is required")
//    @Past(message = "Birthday should be in the past")
//    @Min(value = 18, message = "Age should be at least 18 years")
//    @Max(value = 100, message = "Age should not be greater than 100 years")
    private LocalDate birthday;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentStudyProgram> StudentStudyPrograms;
}
