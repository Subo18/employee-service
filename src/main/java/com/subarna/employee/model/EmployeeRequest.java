package com.subarna.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EmployeeRequest {
    private String empName;
    private String empAddress;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public EmployeeRequest( String empName, String empAddress) {
        this.empName = empName;
        this.empAddress = empAddress;
    }
}
