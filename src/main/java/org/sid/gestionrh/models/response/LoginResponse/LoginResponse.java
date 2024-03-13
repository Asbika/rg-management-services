package org.sid.gestionrh.models.response.LoginResponse;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class LoginResponse {
    private String token;
}
