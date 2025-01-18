package com.subarna.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponse {
    private Long empId;
    private String empName;
    private String empAddress;

    public EmployeeResponse(Long empId, String empName, String empAddress) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
    }
}
