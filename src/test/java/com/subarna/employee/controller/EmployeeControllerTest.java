package com.subarna.employee.controller;

import com.subarna.employee.error.EmployeeNotFoundException;
import com.subarna.employee.model.EmployeeRequest;
import com.subarna.employee.model.EmployeeResponse;
import com.subarna.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    public EmployeeControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getEmployee_shouldReturnEmployeeResponse_whenIdIsProvided() throws Exception {
        EmployeeResponse response = new EmployeeResponse();
        response.setEmpId(1L);
        response.setEmpName("Subarna");
        response.setEmpAddress("Zaandam");

        when(employeeService.getEmployeeDetails(1L)).thenReturn(List.of(response));

        mockMvc.perform(get("/v1/employee")
                .param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].empId").value(1L))
                .andExpect(jsonPath("$[0].empName").value("Subarna"))
                .andExpect(jsonPath("$[0].empAddress").value("Zaandam"));
    }

    @Test
    void getEmployee_shouldReturnAllEmployeeResponses_whenIdIsNotProvided() throws Exception {
        EmployeeResponse response1 = new EmployeeResponse();
        response1.setEmpId(1L);
        response1.setEmpName("Subarna");
        response1.setEmpAddress("Zaandam");

        EmployeeResponse response2 = new EmployeeResponse();
        response2.setEmpId(2L);
        response2.setEmpName("SPaul");
        response2.setEmpAddress("Almere");

        when(employeeService.getEmployeeDetails(null)).thenReturn(List.of(response1, response2));

        mockMvc.perform(get("/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].empId").value(1L))
                .andExpect(jsonPath("$[0].empName").value("Subarna"))
                .andExpect(jsonPath("$[0].empAddress").value("Zaandam"))
                .andExpect(jsonPath("$[1].empId").value(2L))
                .andExpect(jsonPath("$[1].empName").value("SPaul"))
                .andExpect(jsonPath("$[1].empAddress").value("Almere"));
    }

    @Test
    void createEmployee_shouldReturnSuccessMessage_whenEmployeeRequestIsValid() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("Subarna");
        request.setEmpAddress("Zaandam");

        when(employeeService.createEmployee(any(EmployeeRequest.class))).thenReturn("Employee created successfully");

        mockMvc.perform(post("/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"empName\":\"Subarna\",\"empAddress\":\"Zaandam\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee created successfully"));
    }

    @Test
    void updateEmployee_shouldReturnSuccessMessage_whenEmployeeExists() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("Subarna");
        request.setEmpAddress("Zaandam");

        when(employeeService.updateEmployee(eq(1L), any(EmployeeRequest.class))).thenReturn("Employee updated successfully");

        mockMvc.perform(put("/v1/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"empName\":\"Subarna\",\"empAddress\":\"Zaandam\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee updated successfully"));
    }

    @Test
    void deleteEmployee_shouldReturnSuccessMessage_whenEmployeeExists() throws Exception {
        when(employeeService.deleteEmployee(1L)).thenReturn("Employee deleted successfully");

        mockMvc.perform(delete("/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee deleted successfully"));
    }

    @Test
    void createEmployee_shouldReturnBadRequest_whenEmployeeRequestIsInvalid() throws Exception {
        mockMvc.perform(post("/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"empName\":\"\",\"empAddress\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
    
}