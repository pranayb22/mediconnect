package com.mediconnect.patientservice.Exception;

public class PatientAlreadyExistException extends RuntimeException{
    public PatientAlreadyExistException(String message){
        super(message);
    }
}
