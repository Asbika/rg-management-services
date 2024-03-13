package org.sid.gestionrh.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.gestionrh.entities.Employee;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeRequest;
import org.sid.gestionrh.models.response.EmployeeResponse;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends ApplicationMapper<EmployeeRequest, EmployeeResponse, Employee>{
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    Employee addRequestToEntity(EmployeeAddRequest companyEntityAddRequest);
}
