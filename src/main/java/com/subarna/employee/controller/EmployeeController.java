package com.subarna.employee.controller;

import com.subarna.employee.model.EmployeeResponse;
import com.subarna.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployee(
            @RequestParam(value = "employeeId", required = false) Long id)
    {
        return employeeService.getEmployeeDetails(id);
    }
}
