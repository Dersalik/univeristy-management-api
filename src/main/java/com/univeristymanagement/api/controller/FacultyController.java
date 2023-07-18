package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Dto.FacultyUpdateDto;
import com.univeristymanagement.api.service.impl.FacultyServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody @Valid  FacultyCreateDto facultyDto) {
       FacultyDto savedFaculty= facultyService.createFaculty(facultyDto);

         return ResponseEntity.ok(savedFaculty);
    }

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {

       List<FacultyDto> faculties= facultyService.getAllFaculties();
         return ResponseEntity.ok(faculties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById( @PathVariable Long id) {

        FacultyDto faculty= facultyService.getFacultyById(id);

        if(faculty==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacultyById( @PathVariable Long id) {

        boolean deleted= facultyService.deleteFacultyById(id);

        if (deleted) {
            return ResponseEntity.ok("Faculty deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFacultyById(@PathVariable Long id,@Valid @RequestBody FacultyUpdateDto facultyDto) {

        FacultyDto updatedFaculty= facultyService.updateFaculty(id, facultyDto);

        if (updatedFaculty != null) {
            return ResponseEntity.ok(updatedFaculty);
        } else {
            return ResponseEntity.notFound().build();
        }

    }



}
