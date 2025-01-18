package com.subarna.employee.controller;

import com.subarna.employee.model.EmployeeRequest;
import com.subarna.employee.model.EmployeeResponse;
import com.subarna.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(value = "employeeId", required = false) Long id) {
        return employeeService.getEmployeeDetails(id);
    }

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRequest));
    }

    @PutMapping("/{id}")
    public String updateEmployee(
            @PathVariable(name = "id") Long empId,
            @RequestBody @Valid EmployeeRequest employeeRequest
    ) {
        return employeeService.updateEmployee(empId, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable(name = "id") Long empId
    ) {
        return employeeService.deleteEmployee(empId);
    }
}
