package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotBlank(message = "Department name required")
    private String departmentName;
    @NotBlank(message = "Department location is required")
    private String location;
}
