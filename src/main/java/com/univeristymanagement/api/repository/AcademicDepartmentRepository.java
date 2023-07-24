package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.Academic;
import com.univeristymanagement.api.model.AcademicDepartment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicDepartmentRepository extends JpaRepository<AcademicDepartment, Long> {

    /**
     * Find academic department by Id and fetch academics along with it
     * @param id
     * @return AcademicDepartment
     */
    @EntityGraph(attributePaths = {"academics"})
    Optional<AcademicDepartment> findAcademicDepartmentById(Long id);

}
