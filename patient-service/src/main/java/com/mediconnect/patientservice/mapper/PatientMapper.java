package com.mediconnect.patientservice.mapper;

import com.mediconnect.patientservice.Entity.Patient;
import com.mediconnect.patientservice.patientdto.PatientDto;

public class PatientMapper {

    public static PatientDto toDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setGender(patient.getGender());
        patientDto.setAddress(patient.getAddress());
        patientDto.setAge(patient.getAge());
        patientDto.setEmail(patient.getEmail());
        patientDto.setPhoneNumber(patient.getPhoneNumber());
        return patientDto;
    }

public static Patient toEntity(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setGender(patientDto.getGender());
        patient.setAddress(patientDto.getAddress());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setAge(patientDto.getAge());
        patient.setEmail(patientDto.getEmail());
        return patient;
}
}
