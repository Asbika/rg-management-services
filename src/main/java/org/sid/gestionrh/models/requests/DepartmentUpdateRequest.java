package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class DepartmentUpdateRequest {

        @NotNull(message = "Id is required")
        private Long Id;
        @NotBlank(message = "Department name required")
        private String Name;
        @NotBlank(message = "Department location is required")
        private String location;
}
