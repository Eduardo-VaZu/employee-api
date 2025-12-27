package com.erp.employee_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.erp.employee_api.dto.EmployeeRequest;
import com.erp.employee_api.dto.EmployeeResponse;
import com.erp.employee_api.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Management", description = "Operations CRUD para gestion de empleados")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Create an employee", description = "Create an employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an employee", description = "Get an employee by ID")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Update an employee by ID")
    public EmployeeResponse updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Delete an employee by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }
}
