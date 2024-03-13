package org.sid.gestionrh.services;

import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.models.requests.DepartmentAddRequest;
import org.sid.gestionrh.models.requests.DepartmentUpdateRequest;
import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeUpdateRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;

public interface DepartmentService extends CrudServices<DepartmentAddRequest, DepartmentResponse, DepartmentUpdateRequest,Long>{

    DepartmentResponse get(String location);
    DepartmentResponse getByName(String name);
}
