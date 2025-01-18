package com.subarna.employee.mapper;

import com.subarna.employee.entity.Employee;
import com.subarna.employee.model.EmployeeRequest;
import com.subarna.employee.model.EmployeeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Test
    void toDto_shouldMapEmployeeToEmployeeResponse() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("S Bose");
        employee.setAddress("Zaandam");

        EmployeeResponse response = employeeMapper.toDto(employee);

        assertEquals(1L, response.getEmpId());
        assertEquals("S Bose", response.getEmpName());
        assertEquals("Zaandam", response.getEmpAddress());
    }

    @Test
    void toEntity_shouldMapEmployeeRequestToEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("S Bose");
        request.setEmpAddress("Zaandam");

        Employee employee = employeeMapper.toEntity(request);

        assertNull(employee.getId());
        assertEquals("S Bose", employee.getName());
        assertEquals("Zaandam", employee.getAddress());
    }

}