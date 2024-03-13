package org.sid.gestionrh.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Data
public class PositionAddRequest {
    @NotBlank(message = "Position title is required")
    private String title;
    @NotBlank(message = "Position description is required")
    private String description;
}

