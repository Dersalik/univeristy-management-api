package com.univeristymanagement.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Entity
@Table(name = "academic_department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDepartment {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
//    @Max(value = 70, message = "Name should not be greater than 70 characters")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "academicDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Academic> academics;

    @OneToMany(mappedBy = "academicDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyProgram> studyPrograms;
}
