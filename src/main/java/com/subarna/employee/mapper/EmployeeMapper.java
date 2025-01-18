package com.subarna.employee.mapper;

import com.subarna.employee.entity.Employee;
import com.subarna.employee.model.EmployeeRequest;
import com.subarna.employee.model.EmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public EmployeeResponse toDto(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getAddress()
        );
    }

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();

        employee.setAddress(employeeRequest.getEmpAddress());
        employee.setName(employeeRequest.getEmpName());

        return employee;
    }
}
