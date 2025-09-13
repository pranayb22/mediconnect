package com.mediconnect.doctorservice.exception;

import com.mediconnect.doctorservice.repository.DoctorRepository;

public class DoctorNotFoundException  extends RuntimeException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
