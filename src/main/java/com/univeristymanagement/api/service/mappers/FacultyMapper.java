package com.univeristymanagement.api.service.mappers;

import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Faculty;

public class FacultyMapper {

    public static FacultyDto facultyToDto(Faculty faculty){

        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(faculty.getId());
        facultyDto.setName(faculty.getName());
        facultyDto.setDescription(faculty.getDescription());
        facultyDto.setFounder(faculty.getFounder());
        facultyDto.setEstablishedDate(faculty.getEstablishedDate());

        return facultyDto;
    }



    public static Faculty createDtoToFaculty(FacultyCreateDto facultyCreateDto){
        Faculty faculty = new Faculty();
        faculty.setName(facultyCreateDto.getName());
        faculty.setDescription(facultyCreateDto.getDescription());
        faculty.setFounder(facultyCreateDto.getFounder());
        faculty.setEstablishedDate(facultyCreateDto.getEstablishedDate());

        return faculty;
    }
}
