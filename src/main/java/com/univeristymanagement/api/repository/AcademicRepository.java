package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.Academic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicRepository extends JpaRepository<Academic, Long> {
}
