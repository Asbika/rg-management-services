package org.sid.gestionrh.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Employee;
import org.sid.gestionrh.models.requests.DepartmentAddRequest;
import org.sid.gestionrh.models.requests.DepartmentRequest;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends ApplicationMapper<DepartmentRequest, DepartmentResponse, Department>{
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    Department addRequestToEntity(DepartmentAddRequest departmentAddRequest);
}
