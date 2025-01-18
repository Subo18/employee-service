package com.subarna.employee.service;

import com.subarna.employee.entity.Employee;
import com.subarna.employee.error.EmployeeNotFoundException;
import com.subarna.employee.mapper.EmployeeMapper;
import com.subarna.employee.model.EmployeeRequest;
import com.subarna.employee.model.EmployeeResponse;
import com.subarna.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployeeDetails_shouldReturnEmployeeResponse_whenIdIsProvided() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("S Bose");
        employee.setAddress("Zaandam");

        EmployeeResponse response = new EmployeeResponse();
        response.setEmpId(1L);
        response.setEmpName("S Bose");
        response.setEmpAddress("Zaandam");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(employee)).thenReturn(response);

        List<EmployeeResponse> result = employeeService.getEmployeeDetails(1L);

        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
    }

    @Test
    void getEmployeeDetails_shouldReturnAllEmployeeResponses_whenIdIsNotProvided() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setName("S Bose");
        employee1.setAddress("Zaandam");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setName("S Paul");
        employee2.setAddress("Almere");

        EmployeeResponse response1 = new EmployeeResponse();
        response1.setEmpId(1L);
        response1.setEmpName("S Bose");
        response1.setEmpAddress("Zaandam");

        EmployeeResponse response2 = new EmployeeResponse();
        response2.setEmpId(2L);
        response2.setEmpName("S Paul");
        response2.setEmpAddress("Almere");

        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));
        when(employeeMapper.toDto(employee1)).thenReturn(response1);
        when(employeeMapper.toDto(employee2)).thenReturn(response2);

        List<EmployeeResponse> result = employeeService.getEmployeeDetails(null);

        assertEquals(2, result.size());
        assertTrue(result.contains(response1));
        assertTrue(result.contains(response2));
    }

    @Test
    void createEmployee_shouldReturnSuccessMessage_whenEmployeeRequestIsValid() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("S Bose");
        request.setEmpAddress("Zaandam");

        Employee employee = new Employee();
        employee.setName("S Bose");
        employee.setAddress("Zaandam");

        when(employeeMapper.toEntity(request)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);

        String result = employeeService.createEmployee(request);

        assertEquals("Employee created successfully", result);
    }

    @Test
    void updateEmployee_shouldReturnSuccessMessage_whenEmployeeExists() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("S Bose");
        request.setEmpAddress("Zaandam");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("S Paul");
        employee.setAddress("Almere");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        String result = employeeService.updateEmployee(1L, request);

        assertEquals("Employee updated successfully", result);
        assertEquals("S Bose", employee.getName());
        assertEquals("Zaandam", employee.getAddress());
    }

    @Test
    void updateEmployee_shouldThrowException_whenEmployeeDoesNotExist() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("S Bose");
        request.setEmpAddress("Zaandam");

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(1L, request));
    }

    @Test
    void deleteEmployee_shouldReturnSuccessMessage_whenEmployeeExists() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("S Bose");
        employee.setAddress("Zaandam");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        String result = employeeService.deleteEmployee(1L);

        assertEquals("Employee deleted successfully", result);
    }

    @Test
    void deleteEmployee_shouldThrowException_whenEmployeeDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(1L));
    }
}