package org.sid.gestionrh.models.response;

import lombok.Builder;
import lombok.Data;
import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Position;

import java.util.Date;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date employeeDate;
    private PositionResponse position;
    private DepartmentResponse department;
}
