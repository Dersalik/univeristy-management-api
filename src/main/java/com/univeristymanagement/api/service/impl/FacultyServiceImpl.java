package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Dto.FacultyUpdateDto;
import com.univeristymanagement.api.model.Faculty;
import com.univeristymanagement.api.repository.FacultyRepository;
import com.univeristymanagement.api.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository facultyRepository;
    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository){
        this.facultyRepository = facultyRepository;
    }

    private FacultyDto facultyToDto(Faculty faculty){

        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(faculty.getId());
        facultyDto.setName(faculty.getName());
        facultyDto.setDescription(faculty.getDescription());
        facultyDto.setFounder(faculty.getFounder());
        facultyDto.setEstablishedDate(faculty.getEstablishedDate());

        return facultyDto;
    }

    private Faculty createDtoToFaculty(FacultyCreateDto facultyCreateDto){
        Faculty faculty = new Faculty();
        faculty.setName(facultyCreateDto.getName());
        faculty.setDescription(facultyCreateDto.getDescription());
        faculty.setFounder(facultyCreateDto.getFounder());
        faculty.setEstablishedDate(facultyCreateDto.getEstablishedDate());
        return faculty;
    }

    @Override
    public FacultyDto createFaculty(FacultyCreateDto facultyDto) {
        Faculty faculty = createDtoToFaculty(facultyDto);
        facultyRepository.save(faculty);
        return facultyToDto(faculty);

    }

    @Override
    public List<FacultyDto> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDto> facultyDtos = faculties.stream().map(this::facultyToDto).collect(Collectors.toList());
        return facultyDtos;
    }

    @Override
    public FacultyDto getFacultyById(Long id) {

        if(facultyRepository.existsById(id) == false)
            return null;

        return facultyToDto(facultyRepository.findById(id).get());
    }

    @Override
    public boolean deleteFacultyById(Long id) {

        if(facultyRepository.existsById(id) == false)
            return false;

        facultyRepository.deleteById(id);
        return true;
    }

    @Override
    public FacultyDto updateFaculty(Long id, FacultyUpdateDto facultyDto) {

        if(facultyRepository.existsById(id) == false)
            return null;



        Faculty faculty = facultyRepository.findById(id).get();
        faculty.setName(facultyDto.getName());
        faculty.setDescription(facultyDto.getDescription());
        faculty.setFounder(facultyDto.getFounder());
        faculty.setEstablishedDate(facultyDto.getEstablishedDate());
        facultyRepository.save(faculty);
        return facultyToDto(faculty);
    }
}
