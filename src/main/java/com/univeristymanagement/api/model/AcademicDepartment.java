package com.univeristymanagement.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "academicDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Academic> academics;

    @OneToMany(mappedBy = "academicDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyProgram> studyPrograms;
}
