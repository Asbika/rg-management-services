package org.sid.gestionrh.models.requests.LoginRequest;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class LoginRequest {
    private String email;
    private String password;
}
