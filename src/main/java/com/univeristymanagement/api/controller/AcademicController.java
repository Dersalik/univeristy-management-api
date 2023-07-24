package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicUpdateDto;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.impl.AcademicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/academics")
@Tag(name = "Academic", description = "Academic API")
@ApiResponses(
        value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        }
)
public class AcademicController {

    private AcademicService academicService;

    public AcademicController(AcademicServiceImpl academicService) {
        this.academicService = academicService;
    }


    @GetMapping("/")
    @Operation(summary = "Get all academics")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Found the academics", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<AcademicDto>> getAcademic(){
        List<AcademicDto> academics = academicService.getAllAcademics();
        return new ResponseEntity<>(academics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get academic by id")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Found the academic", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Academic not found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    public ResponseEntity<AcademicDto> getAcademicById(Long id){
        AcademicDto academic = academicService.getAcademicById(id);
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete academic by id")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Deleted the academic", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Academic not found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    public ResponseEntity<AcademicDto> deleteAcademicById(Long id){
        AcademicDto academic = academicService.deleteAcademicById(id);
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update academic by id")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Updated the academic", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Academic not found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    public ResponseEntity<AcademicDto> updateAcademicById(Long id,@Valid @RequestBody AcademicUpdateDto academicDto){
        AcademicDto academic = academicService.updateAcademic(id, academicDto);
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }

}
