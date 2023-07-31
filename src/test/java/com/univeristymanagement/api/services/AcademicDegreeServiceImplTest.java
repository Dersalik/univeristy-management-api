package com.univeristymanagement.api.services;


import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.AcademicDegree;
import com.univeristymanagement.api.repository.AcademicDegreeRepository;
import com.univeristymanagement.api.repository.StudyProgramDegreeRepository;
import com.univeristymanagement.api.service.impl.AcademicDegreeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.univeristymanagement.api.model.enums.DegreeName;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class AcademicDegreeServiceImplTest {

    @Mock
    private AcademicDegreeRepository academicDegreeRepository;

    @Mock
    private StudyProgramDegreeRepository studyProgramDegreeRepository;

    @InjectMocks
    private AcademicDegreeServiceImpl academicDegreeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAcademicDegreeById_ValidId_ReturnsAcademicDegreeDto() {
        // Arrange
        Long academicDegreeId = 1L;
        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setId(academicDegreeId);
        academicDegree.setDegreeName(DegreeName.BACHELOR_OF_SCIENCE);

        when(academicDegreeRepository.existsById(academicDegreeId)).thenReturn(true);
        when(academicDegreeRepository.findById(1L)).thenReturn(Optional.of(academicDegree));

        // Act
        AcademicDegreeDto academicDegreeDto = academicDegreeService.getAcademicDegreeById(academicDegreeId);

        // Assert
        assertNotNull(academicDegreeDto);
        assertEquals(academicDegreeId, academicDegreeDto.getId());
        assertEquals(DegreeName.BACHELOR_OF_SCIENCE, academicDegreeDto.getDegreeName());
    }

    @Test
    public void testGetAcademicDegreeById_InvalidId_ThrowsResourceNotFoundException() {
        // Arrange
        Long invalidId = 999L;
        when(academicDegreeRepository.existsById(invalidId)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> academicDegreeService.getAcademicDegreeById(invalidId));
    }

    @Test
    public void testGetAllAcademicDegrees_ReturnsListOfAcademicDegreeDto() {

        // Arrange
        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setId(1L);
        academicDegree.setDegreeName(DegreeName.BACHELOR_OF_SCIENCE);

        AcademicDegree academicDegree2 = new AcademicDegree();
        academicDegree2.setId(2L);
        academicDegree2.setDegreeName(DegreeName.MASTER_OF_ARTS);

        List<AcademicDegree> academicDegrees = new ArrayList<>() ;
        academicDegrees.add(academicDegree);
        academicDegrees.add(academicDegree2);

//        when(academicDegreeRepository.existsById(ArgumentMatchers.any(Long.class))).thenReturn(true);

        when(academicDegreeRepository.findAll()).thenReturn(academicDegrees);

        // Act
        List<AcademicDegreeDto> academicDegreeDtos = academicDegreeService.getAllAcademicDegrees();

        // Assert
        assertNotNull(academicDegreeDtos);
        assertEquals(2, academicDegreeDtos.size());
        assertEquals(DegreeName.BACHELOR_OF_SCIENCE, academicDegreeDtos.get(0).getDegreeName());
        assertEquals(DegreeName.MASTER_OF_ARTS, academicDegreeDtos.get(1).getDegreeName());
    }
    @Test
    public void testacademicDegreeExists_InvalidId_ThrowsResourceNotFoundException(){
        // Arrange
        Long academicDegreeId = 1L;
        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setId(academicDegreeId);
        academicDegree.setDegreeName(DegreeName.BACHELOR_OF_SCIENCE);

        when(academicDegreeRepository.existsById(academicDegreeId)).thenReturn(false);




        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> academicDegreeService.academicDegreeExists(academicDegreeId));

    }


    @Test
    public void testacademicDegreeExists_ValidId() {
        // Arrange
        Long academicDegreeId = 1L;
        AcademicDegree academicDegree = new AcademicDegree();
        academicDegree.setId(academicDegreeId);
        academicDegree.setDegreeName(DegreeName.BACHELOR_OF_SCIENCE);

        when(academicDegreeRepository.existsById(academicDegreeId)).thenReturn(true);

        // Act
        academicDegreeService.academicDegreeExists(academicDegreeId);

        // Assert
        assertDoesNotThrow(() -> academicDegreeService.academicDegreeExists(academicDegreeId));
    }

}