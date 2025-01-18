package com.subarna.employee.service;

import com.subarna.employee.entity.Employee;
import com.subarna.employee.error.EmployeeNotFoundException;
import com.subarna.employee.mapper.EmployeeMapper;
import com.subarna.employee.model.EmployeeRequest;
import com.subarna.employee.model.EmployeeResponse;
import com.subarna.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    public List<EmployeeResponse> getEmployeeDetails(Long id) {
        if(id != null) {
            Employee employee = findEmployee(id);
            return List.of(employeeMapper.toDto(employee));
        }
        else {
            return employeeRepository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public String createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        employeeRepository.save(employee);
        return "Employee created successfully";
    }

    public String updateEmployee(Long empId, EmployeeRequest employeeRequest) {
        Employee employee = findEmployee(empId);

        employee.setName(employeeRequest.getEmpName());
        employee.setAddress(employeeRequest.getEmpAddress());

        employeeRepository.save(employee);

        return "Employee updated successfully";
    }
}
