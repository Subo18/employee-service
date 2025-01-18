package com.subarna.employee.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EmployeeRequest {

    @NotNull
    @NotBlank(message = "Name is mandatory")
    @Size(max = 7, message = "Name should not exceed 7 characters")
    private String empName;

    @NotNull
    @NotBlank(message = "Address is mandatory")
    @Size(max = 10, message = "Address should not exceed 10 characters")
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
