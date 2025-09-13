package com.mediconnect.doctorservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    private ResponseEntity<Map<String,Object>> buildResponseEntity(HttpStatus status, String message){
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("timestamp",LocalDateTime.now());
        map.put("error",status.getReasonPhrase());
        return new ResponseEntity<>(map,status);

    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(DoctorNotFoundException ex){
        return buildResponseEntity(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(DoctorAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleAlreadyExists(DoctorAlreadyExistsException ex){
        return buildResponseEntity(HttpStatus.CONFLICT,ex.getMessage());
    }

    @ExceptionHandler(DoctorDeleteException.class)
    public ResponseEntity<Map<String,Object>> handleDeleteException(DoctorDeleteException ex){
        return buildResponseEntity(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<Map<String,Object>> handleGenericException(Exception ex){
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }


}
