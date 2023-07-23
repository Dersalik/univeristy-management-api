package com.univeristymanagement.api.service;

import com.univeristymanagement.api.model.Dto.AcademicDto;
import com.univeristymanagement.api.model.Dto.AcademicRegistrationDTO;

public interface AcademicService {

    AcademicDto registerAcademic(AcademicRegistrationDTO academicDto);
}
