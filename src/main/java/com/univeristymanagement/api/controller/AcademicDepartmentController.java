package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.AcademicDepartmentCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentUpdateDto;
import com.univeristymanagement.api.service.impl.AcademicDepartmentServiceImp;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/academic-departments")
@Tag(name = "Academic Department", description = "Academic Department API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( mediaType = "application/json") }),
})
public class AcademicDepartmentController {

    private final Logger logger ;

    private AcademicDepartmentServiceImp academicDepartmentService;

    @Autowired
    public AcademicDepartmentController(AcademicDepartmentServiceImp academicDepartmentService){
        this.academicDepartmentService = academicDepartmentService;
        logger=Logger.getLogger(AcademicDepartmentController.class.getName());

        logger.info("AcademicDepartmentController created");
    }

    @Operation(summary = "Get all academic departments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AcademicDepartmentDto.class,type = "array"), mediaType = "application/json") }),
    })
    @GetMapping
    public ResponseEntity<List<AcademicDepartmentDto>> getAllAcademicDepartments() {
        logger.info("Get all academic departments");
        return ResponseEntity.ok().body(academicDepartmentService.getAllAcademicDepartments());
    }

    @Operation(summary = "Get academic department by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AcademicDepartmentDto.class,type = "array"), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content( mediaType = "application/json") })
    })
    @GetMapping("/{id}")
    public ResponseEntity<AcademicDepartmentDto> getAcademicDepartmentById(@PathVariable Long id) {
        logger.info("Get academic department by id");

        return ResponseEntity.ok().body(academicDepartmentService.getAcademicDepartmentById(id));
    }

    @Operation(summary = "Create new academic department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AcademicDepartmentDto.class), mediaType = "application/json") }),
    })
    @PostMapping
    public ResponseEntity<AcademicDepartmentDto> createAcademicDepartment(@Valid @RequestBody AcademicDepartmentCreateDto academicDepartmentDto) {
        logger.info("Create academic department");

        return ResponseEntity.status(HttpStatus.CREATED).body(academicDepartmentService.createAcademicDepartment(academicDepartmentDto));

    }


    @Operation(summary = "Delete academic department by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content( mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content( mediaType = "application/json") })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAcademicDepartmentById(@PathVariable Long id) {
        logger.info("Delete academic department by id");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update academic department by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AcademicDepartmentDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content( mediaType = "application/json") })
    })
    @PutMapping("/{id}")
    public ResponseEntity<AcademicDepartmentDto> updateAcademicDepartmentById(@PathVariable Long id, @Valid @RequestBody AcademicDepartmentUpdateDto academicDepartmentDto) {
        logger.info("Update academic department by id");

        return ResponseEntity.ok().body(academicDepartmentService.updateAcademicDepartmentById(id, academicDepartmentDto));
    }


}
