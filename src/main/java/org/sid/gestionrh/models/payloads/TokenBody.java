package org.sid.gestionrh.models.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenBody {
    private String email;
    private String role;
}
