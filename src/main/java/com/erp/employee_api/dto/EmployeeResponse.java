package com.erp.employee_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        BigDecimal salary,
        LocalDateTime createdAt) {

}
