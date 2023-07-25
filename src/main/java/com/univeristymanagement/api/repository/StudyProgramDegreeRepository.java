package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.AcademicDegree;
import com.univeristymanagement.api.model.StudyProgram;
import com.univeristymanagement.api.model.StudyProgramDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StudyProgramDegreeRepository extends JpaRepository<StudyProgramDegree, Long> {

    Set<StudyProgramDegree> findAllByStudyProgramId(Long studyProgramId);

    Optional<StudyProgramDegree> findByAcademicDegreeIdAndStudyProgramId(Long studyProgramId, Long academicDegreeId);

}
