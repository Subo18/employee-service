package com.subarna.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    @GetMapping
    public String getEmployee(
            @RequestParam(value = "employeeId", required = false) String id)
    {
        return "Employee Details";
    }
}
