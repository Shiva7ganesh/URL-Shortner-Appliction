package com.shivaganesh.Url_shortner_Application.exception;

import com.shivaganesh.Url_shortner_Application.DTO.responseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<responseMessage> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new responseMessage(true, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateShortUrlException.class)
    public ResponseEntity<responseMessage> handleDuplicate(DuplicateShortUrlException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new responseMessage(true, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", true,
                        "validationErrors", errors
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<responseMessage> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new responseMessage(true, "Something went wrong"));
    }
}

