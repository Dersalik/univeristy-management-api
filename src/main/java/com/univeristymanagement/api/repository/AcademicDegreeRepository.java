package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.AcademicDegree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Long> {


}
