package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {

    Academic findByEmail(String email);
}
