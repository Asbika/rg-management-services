package org.sid.gestionrh.exceptions;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timeStamp;
}
