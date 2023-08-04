package com.univeristymanagement.api.services.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.univeristymanagement.api.model.Dto.FacultyCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FacultyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createFaculty_whenValidInput_thenReturns200() throws Exception {
        FacultyCreateDto facultyDto = new FacultyCreateDto();
        facultyDto.setName("Engineering");
        facultyDto.setDescription("Engineering faculty");
        facultyDto.setFounder("John Doe");
        facultyDto.setEstablishedDate(LocalDate.of(2020, 1, 1));
        // fill out the rest of the fields

        mockMvc.perform(post("/api/v1/faculties")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(facultyDto)))
                .andExpect(status().isOk());
    }

    // write similar test methods for the rest of the endpoints
}