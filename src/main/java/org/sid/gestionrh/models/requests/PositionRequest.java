package org.sid.gestionrh.models.requests;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.sid.gestionrh.entities.Employee;
import java.util.List;

@Data
public class PositionRequest {
    @NotBlank(message = "Position.title.required")
    private String title;
    @NotBlank(message = "Position description is required")
    private String description;
}
