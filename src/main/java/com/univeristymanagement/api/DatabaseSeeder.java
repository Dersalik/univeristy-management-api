package com.univeristymanagement.api;

import com.univeristymanagement.api.model.AcademicDegree;
import com.univeristymanagement.api.model.enums.DegreeAbbreviation;
import com.univeristymanagement.api.model.enums.DegreeName;
import com.univeristymanagement.api.repository.AcademicDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AcademicDegreeRepository academicDegreeRepository;

    @Autowired
    public DatabaseSeeder(AcademicDegreeRepository academicDegreeRepository) {
        this.academicDegreeRepository = academicDegreeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(academicDegreeRepository.count() > 0) {
            return;
        }

        seedAcademicDegrees();
    }

    private void seedAcademicDegrees() {
        List<AcademicDegree> academicDegrees = Arrays.asList(
                createAcademicDegree(DegreeName.BACHELOR_OF_SCIENCE, DegreeAbbreviation.BS),
                createAcademicDegree(DegreeName.BACHELOR_OF_ARTS, DegreeAbbreviation.BA),
                createAcademicDegree(DegreeName.MASTER_OF_SCIENCE, DegreeAbbreviation.MS),
                createAcademicDegree(DegreeName.MASTER_OF_ARTS, DegreeAbbreviation.MA),
                createAcademicDegree(DegreeName.DOCTOR_OF_PHILOSOPHY, DegreeAbbreviation.PhD)
        );

        academicDegreeRepository.saveAll(academicDegrees);
    }

    private AcademicDegree createAcademicDegree(DegreeName degreeName, DegreeAbbreviation degreeAbbreviation) {
        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setDegreeName(degreeName);
        academicDegree.setDegreeAbbreviation(degreeAbbreviation);
        return academicDegree;
    }
}