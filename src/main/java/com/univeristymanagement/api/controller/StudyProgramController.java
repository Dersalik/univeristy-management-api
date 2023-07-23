package com.univeristymanagement.api.controller;


import com.univeristymanagement.api.model.Dto.StudyProgramCreateDto;
import com.univeristymanagement.api.model.Dto.StudyProgramDto;
import com.univeristymanagement.api.model.Dto.StudyProgramUpdateDto;
import com.univeristymanagement.api.service.StudyProgramService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/study-programs")
@Tag(name = "Study Program", description = "Study Program API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( mediaType = "application/json") }),
})
public class StudyProgramController {

private StudyProgramService studyProgramService;


   @Autowired
    public StudyProgramController(StudyProgramService studyProgramService) {
        this.studyProgramService = studyProgramService;
    }

    @GetMapping({"/"})
    public ResponseEntity<List<StudyProgramDto>> getStudyPrograms(){
        List<StudyProgramDto> studyPrograms = studyProgramService.getAllStudyPrograms();
        return new ResponseEntity<>(studyPrograms, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<StudyProgramDto> getStudyProgramById(Long id){
        StudyProgramDto studyProgram = studyProgramService.getStudyProgramById(id);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<StudyProgramDto> deleteStudyProgramById(Long id){
        StudyProgramDto studyProgram = studyProgramService.deleteStudyProgramById(id);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }


    @PutMapping ({"/{id}"})
    public ResponseEntity<StudyProgramDto> updateStudyProgramById(Long id, @Valid @RequestBody StudyProgramUpdateDto studyProgramDto){
        StudyProgramDto studyProgram = studyProgramService.updateStudyProgram(id, studyProgramDto);
        return new ResponseEntity<>(studyProgram, HttpStatus.OK);
    }

    @PostMapping({"/"})
    public ResponseEntity<StudyProgramDto> createStudyProgram(@Valid @RequestBody StudyProgramCreateDto studyProgramDto){
        StudyProgramDto studyProgram = studyProgramService.createStudyProgram(studyProgramDto);
        return new ResponseEntity<>(studyProgram, HttpStatus.CREATED);
    }
}
