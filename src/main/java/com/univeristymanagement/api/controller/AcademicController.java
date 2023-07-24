package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.advice.ApplicationExceptionHandler;
import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicUpdateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.impl.AcademicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") }),
})
public class AcademicController {

    private AcademicService academicService;

    public AcademicController(AcademicServiceImpl academicService) {
        this.academicService = academicService;
    }


    @GetMapping("/")
    @Operation(summary = "Get all academics")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the academics",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AcademicDto.class))
                            ,mediaType = "application/json"))
    })
    public ResponseEntity<List<AcademicDto>> getAcademic(){
        List<AcademicDto> academics = academicService.getAllAcademics();
        return new ResponseEntity<>(academics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get academic by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the academic", content = @Content(schema = @Schema(implementation = AcademicDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Academic not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<AcademicDto> getAcademicById(@PathVariable Long id){
        AcademicDto academic = academicService.getAcademicById(id);
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete academic by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted the academic", content = @Content(schema = @Schema(implementation = AcademicDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Academic not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<AcademicDto> deleteAcademicById(@PathVariable Long id){
        AcademicDto academic = academicService.deleteAcademicById(id);
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update academic by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated the academic", content = @Content(schema =@Schema(implementation = AcademicDto.class) ,mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Academic not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<AcademicDto> updateAcademicById(@PathVariable Long id,@Valid @RequestBody AcademicUpdateDto academicDto){
        AcademicDto academic = academicService.updateAcademic(id, academicDto);
        return new ResponseEntity<>(academic, HttpStatus.OK);
    }

    @PostMapping("/{id}/add-study-program/{studyProgramId}")
    @Operation(summary = "Add study program to academic")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204"),
                    @ApiResponse(responseCode = "404", description = "Academic or study program not found", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity addStudyProgramToAcademic(@PathVariable Long id, @PathVariable Long studyProgramId){
        academicService.addStudyProgramToAcademic(id, studyProgramId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}/study-programs")
    @Operation(summary = "Get all study programs of academic")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StudyProgramDto.class)),mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Academic not found", content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<List<StudyProgramDto>> getStudyProgramsOfAcademic(@PathVariable Long id){
        List<StudyProgramDto> studyPrograms = academicService.getAllStudyProgramsByAcademicId(id);
        return new ResponseEntity<>(studyPrograms, HttpStatus.OK);
    }




}
