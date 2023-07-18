package com.univeristymanagement.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "faculty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is required")
//    @Max(value = 70, message = "Name should not be greater than 70 characters")
    private String name;
    @NotNull(message = "Description is required")
//    @Max(value = 1000, message = "Description should not be greater than 200 characters")
    private String description;
    @NotNull(message = "Established date is required")
//    @Past(message = "Established date should be in the past")
    private LocalDate establishedDate;
    @NotNull(message = "Founder is required")
//    @Max(value = 70, message = "Founder should not be greater than 70 characters")
    private String founder;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicDepartment> academicDepartments;
}

