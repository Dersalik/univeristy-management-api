package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.advice.ApplicationExceptionHandler;
import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.model.Dto.StudentDto;
import com.univeristymanagement.api.model.Dto.StudentRegistrationDto;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.StudentService;
import com.univeristymanagement.api.service.impl.AcademicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "User", description = "User API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( schema = @Schema(implementation = ApplicationExceptionHandler.ApiResponse.class),mediaType = "application/json") }),
})
public class UserController {


private final AcademicService academicService;
private final StudentService studentService;
   private final Logger logger;
   @Autowired
    public UserController(AcademicService academicService, StudentService studentService) {
        logger=org.slf4j.LoggerFactory.getLogger(UserController.class);
        this.academicService = academicService;
        this.studentService = studentService;
    }

//    @PostMapping("/admin")
//    public ResponseEntity<User> registerAdmin(@RequestBody AdminRegistrationDTO adminDTO) {
//        User registeredAdmin = userService.registerAdmin(adminDTO);
//        return new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
//    }

    @PostMapping("/student")
    @Operation(summary = "Create new student")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Student created", content = @Content(schema = @Schema(implementation = StudentDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "409", description = "Student already exists") }
    )
    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentRegistrationDto studentDTO) {
        StudentDto registeredStudent = studentService.registerStudent(studentDTO);
        return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
    }

    @PostMapping("/academic")
    @Operation(summary = "Create new academic")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Academic created", content = @Content(schema = @Schema(implementation = AcademicDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "409", description = "Academic already exists") }
    )
    public ResponseEntity<AcademicDto> registerAcademic(@RequestBody @Valid AcademicRegistrationDTO academicDTO) {
        AcademicDto registeredAcademic = academicService.registerAcademic(academicDTO);
        logger.info("Academic created with id: {}", registeredAcademic.getId());
        return new ResponseEntity<>(registeredAcademic, HttpStatus.CREATED);
    }






}
