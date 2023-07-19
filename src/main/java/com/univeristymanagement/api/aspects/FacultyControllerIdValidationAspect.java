package com.univeristymanagement.api.aspects;


import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.service.FacultyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FacultyControllerIdValidationAspect {


    private final FacultyService facultyService; // Replace with your actual service class

    @Autowired
    public FacultyControllerIdValidationAspect(FacultyService yourServiceClass) {
        this.facultyService = yourServiceClass;
    }

    @Pointcut("execution(public * com.univeristymanagement.api.controller.FacultyController.deleteFacultyById(..)) || " +
            "execution(public * com.univeristymanagement.api.controller.FacultyController.getFacultyById(..)) || " +
            "execution(public * com.univeristymanagement.api.controller.FacultyController.updateFacultyById(..)) ||"  +
            "execution(public * com.univeristymanagement.api.controller.FacultyController.getAcademicDepartments(..))")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void validateId(JoinPoint result) {

            Long id = (Long) result.getArgs()[0];

            if (!facultyService.isValidId(id) && id!=null) {
                throw new ResourceNotFoundException("Faculty", "id", id );
            }

    }
}
