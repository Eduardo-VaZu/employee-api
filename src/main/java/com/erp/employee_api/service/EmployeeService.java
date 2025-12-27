package com.erp.employee_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erp.employee_api.dto.EmployeeRequest;
import com.erp.employee_api.dto.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeId(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);

    Page<EmployeeResponse> getAllEmployees(Pageable pageable);
}
