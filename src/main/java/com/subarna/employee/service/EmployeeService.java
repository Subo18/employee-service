package com.subarna.employee.service;

import com.subarna.employee.entity.Employee;
import com.subarna.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeDetails(Long id) {
        if(id != null) {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            return List.of(employee);
        }
        else {
            return employeeRepository.findAll();
        }
    }
}
