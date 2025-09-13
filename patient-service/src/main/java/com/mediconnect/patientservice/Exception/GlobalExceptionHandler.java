package com.mediconnect.patientservice.Exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

public ResponseEntity<Map<String,Object>> buildResponseEntity(HttpStatus status, String message){
    Map<String,Object> map = new HashMap<>();
    map.put("status", status.value());
    map.put("message", message);
    map.put("timestamp", LocalDateTime.now());
    map.put("error", status.getReasonPhrase());
    return new ResponseEntity<>(map, status);
}

@ExceptionHandler(PatientNotFoundException.class)
public ResponseEntity<Map<String,Object>> handleNotFound(PatientNotFoundException ex){
    return  buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
}

@ExceptionHandler(PatientAlreadyExistException.class)
public ResponseEntity<Map<String,Object>> handleAlreadyExist(PatientAlreadyExistException ex){
    return  buildResponseEntity(HttpStatus.CONFLICT, ex.getMessage());
}

@ExceptionHandler(PatientDeleteException.class)
public ResponseEntity<Map<String,Object>> handleDeleteException(PatientDeleteException ex){
    return  buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
}

@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String,Object>> handleException(Exception ex){
    return  buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
}



}
