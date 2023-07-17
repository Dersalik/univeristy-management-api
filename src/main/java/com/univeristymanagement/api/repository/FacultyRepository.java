package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
