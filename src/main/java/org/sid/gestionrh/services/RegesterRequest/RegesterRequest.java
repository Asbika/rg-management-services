package org.sid.gestionrh.services.RegesterRequest;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class RegesterRequest {

    private String name;
    private String email;
    private String password;
    private String role;
}
