package com.pollgenerator.exception;

import com.pollgenerator.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        response.setDate(LocalDateTime.now());

        return response;
    }

}
