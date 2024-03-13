package org.sid.gestionrh.exceptions;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        System.out.println("Hicham");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<Object> handleResourceAlreadyExistsException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";")))
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleJwtException(Exception ex, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

}
