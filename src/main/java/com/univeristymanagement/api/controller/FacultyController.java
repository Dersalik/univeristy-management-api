package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import com.univeristymanagement.api.model.Dto.FacultyDto;
import com.univeristymanagement.api.model.Dto.FacultyUpdateDto;
import com.univeristymanagement.api.service.impl.FacultyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Faculty", description = "Faculty API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( mediaType = "application/json") }),
})
@RestController
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    private FacultyServiceImpl facultyService;

    @Autowired
    public FacultyController(FacultyServiceImpl facultyService){
        this.facultyService = facultyService;
    }


    @Operation(summary = "Create new faculty")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FacultyDto.class), mediaType = "application/json") }),
    })
    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody @Valid  FacultyCreateDto facultyDto) {
       FacultyDto savedFaculty= facultyService.createFaculty(facultyDto);

         return ResponseEntity.ok(savedFaculty);
    }

    @Operation(summary = "Get all faculties")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FacultyDto.class,type = "array"), mediaType = "application/json") }),
    })
    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {

       List<FacultyDto> faculties= facultyService.getAllFaculties();
         return ResponseEntity.ok(faculties);
    }

    @Operation(summary = "Get faculty by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FacultyDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") })
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById( @PathVariable Long id) {

        FacultyDto faculty= facultyService.getFacultyById(id);

        if(faculty==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);
    }

    @Operation(summary = "Delete faculty by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacultyById( @PathVariable Long id) {

        boolean deleted= facultyService.deleteFacultyById(id);

        if (deleted) {
            return ResponseEntity.ok("Faculty deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Update faculty by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FacultyDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") })
    })
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
