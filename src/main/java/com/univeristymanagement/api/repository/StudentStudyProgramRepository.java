package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.StudentStudyProgram;
import com.univeristymanagement.api.model.StudyProgramDegree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface StudentStudyProgramRepository extends JpaRepository<StudentStudyProgram, Long> {
    Set<StudentStudyProgram> findAllByStudyProgramId(Long studyProgramId);
    Optional<StudentStudyProgram> findByStudentIdAndStudyProgramId(Long studentId, Long studyProgramId);
}
