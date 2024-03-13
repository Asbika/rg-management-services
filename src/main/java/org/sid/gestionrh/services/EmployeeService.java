package org.sid.gestionrh.services;

import org.sid.gestionrh.models.requests.EmployeeAddRequest;
import org.sid.gestionrh.models.requests.EmployeeRequest;
import org.sid.gestionrh.models.requests.EmployeeUpdateRequest;
import org.sid.gestionrh.models.response.EmployeeResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface EmployeeService extends CrudServices<EmployeeAddRequest, EmployeeResponse, EmployeeUpdateRequest,Long>{

    EmployeeResponse get(String email);
    EmployeeResponse get(String firstName,String lastName);

    List<EmployeeResponse> getByPsition(String positionTitle);

    List<EmployeeResponse> getByDate(Date date1, Date date2);

    void setPhoto(MultipartFile photo, Long employeeId);
    Resource getPhoto(Long employeeId);

    List<EmployeeResponse> getByDepartment(String DepartmentName);
    boolean init();

}
