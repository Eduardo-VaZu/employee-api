package com.erp.employee_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.employee_api.dto.EmployeeRequest;
import com.erp.employee_api.dto.EmployeeResponse;
import com.erp.employee_api.entity.Employee;
import com.erp.employee_api.mapper.EmployeeMapper;
import com.erp.employee_api.repository.EmployeeRepository;

import com.erp.employee_api.exception.EmailAlreadyExistsException;
import com.erp.employee_api.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Employee employee = employeeMapper.toEntity(request);
        employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeId(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!employee.getEmail().equals(request.email()) &&
                employeeRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        employeeMapper.updateEntityFromRequest(request, employee);
        employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toResponse);
    }

}
