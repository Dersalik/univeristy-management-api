package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.service.impl.FacultyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    private FacultyServiceImpl facultyService;

    @Autowired
    public FacultyController(FacultyServiceImpl facultyService){
        this.facultyService = facultyService;
    }


    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody FacultyCreateDto facultyDto) {
       FacultyDto savedFaculty= facultyService.createFaculty(facultyDto);

         return ResponseEntity.ok(savedFaculty);
    }

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {

       List<FacultyDto> faculties= facultyService.getAllFaculties();
         return ResponseEntity.ok(faculties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable Long id) {

        FacultyDto faculty= facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultyById(@PathVariable Long id) {

        facultyService.deleteFacultyById(id);
        return ResponseEntity.ok().build();
    }



}
