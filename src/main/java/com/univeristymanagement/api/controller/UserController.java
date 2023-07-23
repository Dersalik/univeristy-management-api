package com.univeristymanagement.api.controller;

import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;
import com.univeristymanagement.api.service.impl.AcademicServiceImpl;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "User", description = "User API")
@ApiResponses({
        @ApiResponse(responseCode = "500", content = { @Content( mediaType = "application/json") }),
})
public class UserController {


private AcademicServiceImpl academicService;

   @Autowired
    public UserController(AcademicServiceImpl academicService) {
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
    public ResponseEntity<AcademicDto> registerAcademic(@RequestBody @Valid AcademicRegistrationDTO academicDTO) {
        AcademicDto registeredAcademic = academicService.registerAcademic(academicDTO);
        return new ResponseEntity<>(registeredAcademic, HttpStatus.CREATED);
    }




}
