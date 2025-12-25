package com.erp.employee_api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EmployeeRequest(

        @NotBlank(message = "First name is required") 
        String firstname,

        @NotBlank(message = "Last name is required") 
        String lastname,

        @NotBlank(message = "Email is required") 
        String email,

        @NotBlank(message = "Salary is required") 
        @Positive(message = "Salary must be greater than 0") 
        BigDecimal salary) {
}
