package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {

    Optional<Academic> findByEmail(String email);

    boolean existsByEmail(String email);

   // Optional<List<Academic>> findAcademicsByAcademicDepartmentId(Long id);
}
