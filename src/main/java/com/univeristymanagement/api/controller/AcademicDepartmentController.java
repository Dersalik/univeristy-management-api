package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.AcademicDepartmentCreateDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentDto;
import com.univeristymanagement.api.model.Dto.AcademicDepartmentUpdateDto;
import com.univeristymanagement.api.service.impl.AcademicDepartmentImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/academic-departments")
public class AcademicDepartmentController {

    private Logger logger = Logger.getLogger(AcademicDepartmentController.class.getName());

    private AcademicDepartmentImp academicDepartmentService;

    @Autowired
    public AcademicDepartmentController(AcademicDepartmentImp academicDepartmentService){
        this.academicDepartmentService = academicDepartmentService;
    }

    @GetMapping
    public List<AcademicDepartmentDto> getAllAcademicDepartments() {
        logger.info("Get all academic departments");
        return academicDepartmentService.getAllAcademicDepartments();
    }

    @GetMapping("/{id}")
    public AcademicDepartmentDto getAcademicDepartmentById(@PathVariable Long id) {
        logger.info("Get academic department by id");
        return academicDepartmentService.getAcademicDepartmentById(id);
    }

    @PostMapping
    public AcademicDepartmentDto createAcademicDepartment(@Valid @RequestBody AcademicDepartmentCreateDto academicDepartmentDto) {
        logger.info("Create academic department");
        return academicDepartmentService.createAcademicDepartment(academicDepartmentDto);
    }


    @DeleteMapping("/{id}")
    public boolean deleteAcademicDepartmentById(@PathVariable Long id) {
        logger.info("Delete academic department by id");
        return academicDepartmentService.deleteAcademicDepartmentById(id);
    }

    @PutMapping("/{id}")
    public AcademicDepartmentDto updateAcademicDepartmentById(@PathVariable Long id, @Valid @RequestBody AcademicDepartmentUpdateDto academicDepartmentDto) {
        logger.info("Update academic department by id");
        return academicDepartmentService.updateAcademicDepartmentById(id, academicDepartmentDto);
    }





}
