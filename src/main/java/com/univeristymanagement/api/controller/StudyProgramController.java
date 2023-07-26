package com.univeristymanagement.api.controller;


import com.univeristymanagement.api.advice.ApplicationExceptionHandler;
import com.univeristymanagement.api.model.Dto.*;
import com.univeristymanagement.api.model.StudentStudyProgram;
import com.univeristymanagement.api.service.StudyProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/study-programs")
@Tag(name = "Study Program", description = "Study Program API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") }),
})
public class StudyProgramController {

private final StudyProgramService studyProgramService;

private final Logger logger ;
   @Autowired
    public StudyProgramController(StudyProgramService studyProgramService) {
        this.studyProgramService = studyProgramService;
       logger=org.slf4j.LoggerFactory.getLogger(StudyProgramController.class);
    }

    @GetMapping({"/"})
    @Operation(summary = "Get all study programs")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = StudyProgramDto.class)),mediaType = "application/json"))
    })
    public ResponseEntity<List<StudyProgramDto>> getStudyPrograms(){
        List<StudyProgramDto> studyPrograms = studyProgramService.getAllStudyPrograms();
        logger.info("Get all study programs");
        return new ResponseEntity<>(studyPrograms, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    @Operation(summary = "Get study program by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(implementation = StudyProgramDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    , content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<StudyProgramDto> getStudyProgramById(@PathVariable Long id){
        StudyProgramDto studyProgram = studyProgramService.getStudyProgramById(id);
        logger.info("Get study program by id:" + id);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }
    @Operation(summary = "Delete study program by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200"
                    , content = @Content(schema = @Schema(implementation = StudyProgramDto.class)
                    ,mediaType = "application/json")),
            @ApiResponse(responseCode = "404"
                    , description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class)
                    ,mediaType = "application/json") })
    })
    @DeleteMapping({"/{id}"})
    public ResponseEntity<StudyProgramDto> deleteStudyProgramById(@PathVariable Long id){
        StudyProgramDto studyProgram = studyProgramService.deleteStudyProgramById(id);

        logger.info("Delete study program by id:" + id);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }

    @Operation(summary = "Update study program by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(implementation = StudyProgramDto.class)
                    ,mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class)
                    ,mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping ({"/{id}"})
    public ResponseEntity<StudyProgramDto> updateStudyProgramById(@PathVariable Long id, @Valid @RequestBody StudyProgramUpdateDto studyProgramDto){
        StudyProgramDto studyProgram = studyProgramService.updateStudyProgram(id, studyProgramDto);

        logger.info("Update study program by id:" + id);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }

    @Operation(summary = "Create study program")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = StudyProgramDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping({"/"})
    public ResponseEntity<StudyProgramDto> createStudyProgram(@Valid @RequestBody StudyProgramCreateDto studyProgramDto){
        StudyProgramDto studyProgram = studyProgramService.createStudyProgram(studyProgramDto);

        logger.info("Create study program");

        return new ResponseEntity<>(studyProgram, HttpStatus.CREATED);
    }



    @GetMapping({"/{id}/academic-degrees"})
    @Operation(summary = "Get all academic degrees by study program id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = AcademicDegreeDto.class)),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<Set<AcademicDegreeDto>> getAcademicDegreesByStudyProgramId(@PathVariable Long id){
        Set<AcademicDegreeDto> academicDegrees = studyProgramService.getAcademicDegreesByStudyProgramId(id);
        logger.info("Get all academic degrees by study program id:" + id);
        return new ResponseEntity<>(academicDegrees, HttpStatus.OK);
    }

    @PostMapping({"/{id}/academic-degrees/{academicDegreeId}"})
    @Operation(summary = "Add academic degree to study program")
    @ApiResponses({
            @ApiResponse(responseCode="204", description = "Academic degree added to study program"),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<StudyProgramDto> addAcademicDegreeToStudyProgram(@PathVariable Long id, @PathVariable Long academicDegreeId){
         studyProgramService.addAcademicDegreeToStudyProgram(academicDegreeId, id);

        logger.info("Add academic degree to study program" );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{id}/academic-degrees/{academicDegreeId}"})
    @Operation(summary = "Delete academic degree from study program")
    @ApiResponses({
            @ApiResponse(responseCode="204", description = "Academic degree deleted from study program"),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<StudyProgramDto> deleteAcademicDegreeFromStudyProgram(@PathVariable Long id, @PathVariable Long academicDegreeId){
         studyProgramService.deleteAcademicDegreeFromStudyProgram(academicDegreeId, id);

        logger.info("Delete academic degree from study program" +" Study Program Id:"+ id+ " " +"Academic Degree Id:"+ academicDegreeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping({"/{id}/students/{studentId}"})
    @Operation(summary = "Add student to study program")
    @ApiResponses({
            @ApiResponse(responseCode="204", description = "Student added to study program"),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<?> addStudentToStudyProgram(@PathVariable Long id, @PathVariable Long studentId
    ,@RequestBody StudentStudyProgramCreateDto studentStudyProgramCreateDto){
         studyProgramService.addStudentToStudyProgram(id, studentId, studentStudyProgramCreateDto);

        logger.info("Add student to study program" );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping({"/{id}/students"})
    @Operation(summary = "Get all students by study program id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = StudentDto.class)),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Study program not found"
                    ,content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<Set<StudentStudyProgramDto>> getStudentsByStudyProgramId(@PathVariable Long id){
        Set<StudentStudyProgramDto> students = studyProgramService.getEnrollmentsByStudyProgramId(id);
        logger.info("Get all students by study program id:" + id);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
