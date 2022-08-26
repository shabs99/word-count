package com.example.wordcount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    private String FILE_FAILURE = "FAILED TO PROCESS FILE";
    private String INTERNAL_SERVER_ERROR = "BAD_REQUEST";

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
            (IOException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(FILE_FAILURE, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException
            (Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(INTERNAL_SERVER_ERROR, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
