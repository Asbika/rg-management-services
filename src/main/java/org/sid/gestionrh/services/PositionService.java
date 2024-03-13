package org.sid.gestionrh.services;

import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeUpdateRequest;
import org.sid.gestionrh.models.requests.PositionAddRequest;
import org.sid.gestionrh.models.requests.PositionUpdateRequest;
import org.sid.gestionrh.models.response.DepartmentResponse;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.sid.gestionrh.models.response.PositionResponse;

public interface PositionService extends CrudServices<PositionAddRequest, PositionResponse, PositionUpdateRequest,Long>{

    PositionResponse get(String title);
}
