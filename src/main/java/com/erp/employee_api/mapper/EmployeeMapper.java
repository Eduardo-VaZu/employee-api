package com.erp.employee_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.erp.employee_api.entity.Employee;
import com.erp.employee_api.dto.EmployeeRequest;
import com.erp.employee_api.dto.EmployeeResponse;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true) // Deja que Spring Audit lo maneje
    @Mapping(target = "updatedAt", ignore = true) // Deja que Spring Audit lo maneje
    @Mapping(target = "phone", ignore = true) // Ignora phone porque no viene en el Request
    Employee toEntity(EmployeeRequest request);

    EmployeeResponse toResponse(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "phone", ignore = true)
    void updateEntityFromRequest(EmployeeRequest request, @MappingTarget Employee employee);
}