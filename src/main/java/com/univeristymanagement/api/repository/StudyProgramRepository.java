package com.univeristymanagement.api.repository;

import com.univeristymanagement.api.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
}
