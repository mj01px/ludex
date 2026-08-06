package com.ludex.backend.common.web;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(EntityNotFoundException ex) {
        return ErrorResponse.of("NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorResponse.FieldIssue> details = ex.getBindingResult().getFieldErrors().stream()
                .map(this::toFieldIssue)
                .toList();
        return ErrorResponse.of("VALIDATION_ERROR", "Validation failed", details);
    }

    private ErrorResponse.FieldIssue toFieldIssue(FieldError fieldError) {
        return new ErrorResponse.FieldIssue(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
