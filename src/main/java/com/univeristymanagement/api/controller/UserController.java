package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.service.AcademicService;
import com.univeristymanagement.api.service.impl.AcademicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "User", description = "User API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( mediaType = "application/json") }),
})
public class UserController {


private AcademicService academicService;

   private Logger logger;
   @Autowired
    public UserController(AcademicServiceImpl academicService) {
        logger=org.slf4j.LoggerFactory.getLogger(UserController.class);
        this.academicService = academicService;
    }

//    @PostMapping("/admin")
//    public ResponseEntity<User> registerAdmin(@RequestBody AdminRegistrationDTO adminDTO) {
//        User registeredAdmin = userService.registerAdmin(adminDTO);
//        return new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
//    }

//    @PostMapping("/student")
//    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentRegistrationDTO studentDTO) {
//        User registeredStudent = userService.registerStudent(studentDTO);
//        return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
//    }
//
    @PostMapping("/academic")
    @Operation(summary = "Create new academic")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Academic created", content = @Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AcademicDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "409", description = "Academic already exists") }
    )
    public ResponseEntity<AcademicDto> registerAcademic(@RequestBody @Valid AcademicRegistrationDTO academicDTO) {
        AcademicDto registeredAcademic = academicService.registerAcademic(academicDTO);
        logger.info("Academic created with id: {}", registeredAcademic.getId());
        return new ResponseEntity<>(registeredAcademic, HttpStatus.CREATED);
    }






}
