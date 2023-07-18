package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.AcademicDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicDepartmentRepository extends JpaRepository<AcademicDepartment, Long> {
}
