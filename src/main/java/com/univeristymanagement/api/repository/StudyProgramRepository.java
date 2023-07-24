package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {

    Optional<List<StudyProgram>> findAllByAcademicDepartmentId(Long id);

    Optional<List<StudyProgram>> findAllByAcademicId(Long id);
}
