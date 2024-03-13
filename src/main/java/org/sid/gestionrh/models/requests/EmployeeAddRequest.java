package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeAddRequest {
    @NotBlank(message = "Employee first name required")
    private String firstName;
    @NotBlank(message = "Employee last name required")
    private String lastName;
    @Email
    private String email;
    @NotNull(message = "position is required")
    private Long positionId;
    @NotNull(message = "department is required")
    private Long departmentId;
}
