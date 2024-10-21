package com.koerber.pharma.controller;

import com.koerber.pharma.dto.Patient;
import com.koerber.pharma.routing.GatewayController;
import com.koerber.pharma.services.GatewayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(GatewayController.class)
public class GatewayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GatewayService gatewayService;

    @Test
    public void testGetPatients() throws Exception {

        Patient patient1 = new Patient("Manuel", 53);
        Patient patient2 = new Patient("Joana", 32);
        List<Patient> patients = Arrays.asList(patient1, patient2);

        Page<Patient> patientPage = new PageImpl<>(patients, PageRequest.of(0, 10), 2);

        when(gatewayService.getPatients(anyInt(), anyInt())).thenReturn(patientPage);


        mockMvc.perform(get("/pharma/patients")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2))) // Expect 2 patients in the content
                .andExpect(jsonPath("$.content[0].name", is("Manuel"))) // Check the first patient's name
                .andExpect(jsonPath("$.content[0].age", is(53)))        // Check the first patient's age
                .andExpect(jsonPath("$.content[1].name", is("Joana")))  // Check the second patient's name
                .andExpect(jsonPath("$.content[1].age", is(32)))        // Check the second patient's age
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))    // Check pagination info
                .andExpect(jsonPath("$.pageable.pageSize", is(10)));

        verify(gatewayService, times(1)).getPatients(0, 10);
    }

    @Test
    public void testGetPatientsEmptyList_ThrowsException() throws Exception {
        // Arrange: Empty patient list
        List<Patient> patients = Collections.emptyList();
        Page<Patient> patientPage = new PageImpl<>(patients, PageRequest.of(0, 10), 0);

        when(gatewayService.getPatients(anyInt(), anyInt())).thenReturn(patientPage);

        // Act and Assert
        mockMvc.perform(get("/pharma/patients")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0))) // No patients in the content
                .andExpect(jsonPath("$.totalElements", is(0)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)));

        verify(gatewayService, times(1)).getPatients(0, 10);
    }

    @Test
    public void testGetPatientsSortingByAge_ThrowsException() throws Exception {

        Patient patient1 = new Patient("Manuel", 32);
        Patient patient2 = new Patient("Joana", 53);
        List<Patient> patients = Arrays.asList(patient1, patient2);
        Page<Patient> patientPage = new PageImpl<>(patients, PageRequest.of(0, 10), 2);


        when(gatewayService.getPatients(anyInt(), anyInt())).thenReturn(patientPage);


        mockMvc.perform(get("/pharma/patients")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "age,asc") // Simulating sorting by age in ascending order
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].age", is(32))) // Joana comes first (younger)
                .andExpect(jsonPath("$.content[1].age", is(53))) // Manuel comes second (older))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)));

        verify(gatewayService, times(1)).getPatients(0, 10); // Verify service was called once
    }

    @Test
    public void testGetPatientsSortingByName_ThrowsException() throws Exception {

        Patient patient1 = new Patient("Joana", 32);
        Patient patient2 = new Patient("Manuel", 53);
        List<Patient> patients = Arrays.asList(patient1, patient2);
        Page<Patient> patientPage = new PageImpl<>(patients, PageRequest.of(0, 10), 2);

        // Mock the sorted result
        when(gatewayService.getPatients(anyInt(), anyInt())).thenReturn(patientPage);

        // Act and Assert
        mockMvc.perform(get("/pharma/patients")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "name,asc") // Simulating sorting by name
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", is("Joana"))) // Joana comes first (alphabetical)
                .andExpect(jsonPath("$.content[1].name", is("Manuel"))); // Manuel comes second

        verify(gatewayService, times(1)).getPatients(0, 10); // Verify service was called once
    }

}
