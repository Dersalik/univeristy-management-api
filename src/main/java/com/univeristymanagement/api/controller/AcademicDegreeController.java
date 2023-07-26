package com.univeristymanagement.api.controller;


import com.univeristymanagement.api.advice.ApplicationExceptionHandler;
import com.univeristymanagement.api.model.Dto.AcademicDegreeDto;
import com.univeristymanagement.api.service.AcademicDegreeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/academic-degrees")
@Tag(name = "Academic Degree", description = "Academic Degree API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
})
public class AcademicDegreeController {


    private final Logger logger ;


    private final AcademicDegreeService academicDegreeService;

    @Autowired
    public AcademicDegreeController(AcademicDegreeService academicDegreeService){
        this.academicDegreeService = academicDegreeService;
        logger= Logger.getLogger(AcademicDegreeController.class.getName());

        logger.info("AcademicDegreeController created");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get academic degree by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the academic degree", content = @Content(schema = @Schema(implementation = AcademicDegreeDto.class),mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Academic degree not found",content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
    })
    public ResponseEntity<AcademicDegreeDto> getAcademicDegreeById(@PathVariable Long id) {
        logger.info("Get academic degree by id");
        return ResponseEntity.ok(academicDegreeService.getAcademicDegreeById(id));
    }

    @GetMapping
    @Operation(summary = "Get all academic degrees")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the academic degrees", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AcademicDegreeDto.class)),mediaType = "application/json")),
    })
    public ResponseEntity<List<AcademicDegreeDto>> getAllAcademicDegrees() {
        logger.info("Get all academic degrees");
        return ResponseEntity.ok().body(academicDegreeService.getAllAcademicDegrees());
    }



    //since the degree names and abbreviations are available as enums there is no purpose
    // to have an endpoint to create them
//    @PostMapping
//    @Operation(summary = "Create academic degree")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Created the academic degree", content = @Content(schema = @Schema(implementation = AcademicDegreeDto.class),mediaType = "application/json")),
//            @ApiResponse(responseCode = "400", description = "Bad request",content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") })
//    })
//    public ResponseEntity<AcademicDegreeDto> createAcademicDegree(@RequestBody AcademicDegreeCreateDto academicDegreeDto) {
//        logger.info("Create academic degree");
//        return ResponseEntity.ok(academicDegreeService.addAcademicDegree(academicDegreeDto));
//    }

}
