package com.pollgenerator.exception;

import com.pollgenerator.model.response.ExceptionResponse;
import io.swagger.v3.oas.annotations.Hidden;
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
    @Hidden
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        response.setDate(LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(AlreadyVotedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @Hidden
    public ExceptionResponse handleAlreadyVotedException(AlreadyVotedException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setStatusCode(HttpStatus.CONFLICT.value());
        response.setMessage(e.getMessage());
        response.setDate(LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(OptionNotBelongsToPollException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Hidden
    public ExceptionResponse handleOptionNotBelongsToPollException(OptionNotBelongsToPollException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        response.setDate(LocalDateTime.now());

        return response;
    }

    @ExceptionHandler(MultipleVotesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Hidden
    public ExceptionResponse handleMultipleVotesException(MultipleVotesException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        response.setDate(LocalDateTime.now());

        return response;
    }

}
