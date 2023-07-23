package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceAlreadyAssignedException;
import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.AcademicDepartment;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Dto.FacultyUpdateDto;
import com.univeristymanagement.api.model.Faculty;
import com.univeristymanagement.api.repository.AcademicDepartmentRepository;
import com.univeristymanagement.api.repository.FacultyRepository;
import com.univeristymanagement.api.service.FacultyService;
import com.univeristymanagement.api.service.mappers.FacultyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository facultyRepository;
    private AcademicDepartmentRepository academicDepartmentRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, AcademicDepartmentRepository academicDepartmentRepository){
        this.academicDepartmentRepository = academicDepartmentRepository;
        this.facultyRepository = facultyRepository;
    }
    /**
     * Checks if a faculty exists by id
     * @param id
     * @return boolean
     */
    @Override
    public boolean isValidId(Long id) {
        return facultyRepository.existsById(id);
    }
    /**
     * Creates a faculty
     * @param facultyDto
     * @return FacultyDto
     */
    @Override
    public FacultyDto createFaculty(FacultyCreateDto facultyDto) {
        Faculty faculty = FacultyMapper.createDtoToFaculty(facultyDto);

        facultyRepository.save(faculty);
        return FacultyMapper.facultyToDto(faculty);

    }
    /**
     * Gets all faculties
     * @return List<FacultyDto>
     */
    @Override
    public List<FacultyDto> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDto> facultyDtos = faculties.stream()
                .map(FacultyMapper::facultyToDto)
                .collect(Collectors.toList());
        return facultyDtos;
    }

    /**
     * Gets a faculty by id
     * @param id
     * @return FacultyDto
     */
    @Override
    public FacultyDto getFacultyById(Long id) {
        return FacultyMapper.facultyToDto(facultyRepository.findById(id).get());
    }
    /**
     * Deletes a faculty by id
     * @param id
     * @return boolean
     */
    @Override
    public boolean deleteFacultyById(Long id) {

        facultyRepository.deleteById(id);
        return true;

    }
    /**
     * Gets all academic departments by faculty id
     * @param id
     * @return List<AcademicDepartmentDto>
     */
    @Override
    public List<AcademicDepartmentDto> getAllAcademicDepartmentsByFacultyId(Long id) {

        Faculty faculty = facultyRepository.findById(id).get();

        FacultyDto facultyDto = FacultyMapper.facultyToDto(faculty);

        List<AcademicDepartmentDto> academicDepartmentDtos = faculty.getAcademicDepartments()
                .stream().map((k)-> new AcademicDepartmentDto(k.getId(),k.getName(),facultyDto)).collect(Collectors.toList());
        return academicDepartmentDtos;
    }


    /**
     * Updates a faculty
     * @param id
     * @param facultyDto
     * @return
     */
    @Override
    public FacultyDto updateFaculty(Long id, FacultyUpdateDto facultyDto) {

        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setName(facultyDto.getName());
        faculty.setDescription(facultyDto.getDescription());
        faculty.setFounder(facultyDto.getFounder());
        faculty.setEstablishedDate(facultyDto.getEstablishedDate());
        facultyRepository.save(faculty);

        return FacultyMapper.facultyToDto(faculty);
    }

    /**
     * Assigns an academic department to a faculty
     * @param facultyId
     * @param academicDepartmentId
     */
    @Override
    public void assignAcademicDepartmentToFaculty(Long facultyId, Long academicDepartmentId) {
        // Retrieve the Faculty and AcademicDepartment entities
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty ", "id" , facultyId));

        AcademicDepartment department =  academicDepartmentRepository.findById(academicDepartmentId)
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", academicDepartmentId));

        // Assign the academic department to the faculty
        if(faculty.getAcademicDepartments().stream().anyMatch(dept -> dept.getId().equals(academicDepartmentId))) {
            throw new ResourceAlreadyAssignedException("AcademicDepartment", academicDepartmentId,
                    "Faculty", facultyId);
        }

        department.setFaculty(faculty);

        academicDepartmentRepository.save(department);
    }


}
