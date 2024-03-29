package com.example.sleeter.controller;

import com.example.sleeter.dto.ErrorResponse;
import com.example.sleeter.exception.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.sql.SQLException;
import java.util.Arrays;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, SQLException.class})
    protected ResponseEntity<ErrorResponse> handleConflict(Exception exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace())
        ));
    }

    @ExceptionHandler(value = {AuthException.class})
    protected ResponseEntity<ErrorResponse> handleConflict(AuthException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                exception.getError(),
                exception.getHeader(),
                exception.getDescription()
        ));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> handleConflict(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                exception.getBindingResult().getFieldError().getDefaultMessage()
        ));
    }
}

