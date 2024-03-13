package org.sid.gestionrh.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionResponse {
    private Long id;
    private String title;
    private String description;
}
