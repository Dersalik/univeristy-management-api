package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
