package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PositionUpdateRequest {

    @NotBlank(message="Position Id required")
    private Long id;
    @NotBlank(message = "Position title required")
    private String title;
    @NotBlank(message = "Position description is required")
    private String description;
}

