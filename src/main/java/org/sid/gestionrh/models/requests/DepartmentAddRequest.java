package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentAddRequest {
    @NotBlank(message = "Department name required")
    private String name;
    @NotBlank(message = "Department location is required")
    private String location;
}
