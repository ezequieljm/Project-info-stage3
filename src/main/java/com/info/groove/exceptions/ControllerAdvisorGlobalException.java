package com.info.groove.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdvisorGlobalException extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseEntity<Object> handleOrganizationNotFoundException(
        OrganizationNotFoundException ex, WebRequest request, HttpStatus status){

        Map<String,Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrganizationKeyNotEqual.class)
    public ResponseEntity<Object> handleOrganizationKeyNotEqual(OrganizationKeyNotEqual ex, WebRequest request) {
        
        Map<String,Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(
            UserNotFoundException ex,
            WebRequest request,
            HttpStatus status
    ){

        Map<String,Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> handleEventNotFound(EventNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(DuplicateDataError.class)
    public ResponseEntity<Object> handleDuplicateDataError(DuplicateDataError ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TurnNofFoundException.class)
    public ResponseEntity<Object> handleTurnNotFoundException(TurnNofFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDateTimeException.class)
    public ResponseEntity<Object> handleDuplicateDateTime(DuplicateDateTimeException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<String,Object>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("status", status.value());

//        List<String> errors = ex.getBindingResult()
//            .getFieldErrors()
//            .stream()
//            .map(x -> x.getDefaultMessage())
//            .collect(Collectors.toList());

//        body.put("erros", errors);
        return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
    }


}
