package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank(message = "Employee first name required")
    private String firstName;
    @NotBlank(message = "Employee last name required")
    private String lastName;
    @Email
    private String email;
}
