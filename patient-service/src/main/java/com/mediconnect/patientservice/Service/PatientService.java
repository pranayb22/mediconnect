package com.mediconnect.patientservice.Service;

import com.mediconnect.patientservice.Entity.Patient;
import com.mediconnect.patientservice.Exception.ResourceNotFoundException;
import com.mediconnect.patientservice.Repository.PatientRepository;
import com.mediconnect.patientservice.mapper.PatientMapper;
import com.mediconnect.patientservice.patientdto.PatientDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }


    //Get all patients
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper :: toDto)
                .toList();
    }

      //Get By id
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patient is not found by id" +id));
        return PatientMapper.toDto(patient);
    }

    public PatientDto savePatient(PatientDto patientdto) {
        Patient patient = PatientMapper.toEntity(patientdto);
        Patient saved = patientRepository.save(patient);
        return PatientMapper.toDto(saved);
    }

    public PatientDto updatePatient(Long id, PatientDto patientdto) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " +id));

        patient.setFirstName(patientdto.getFirstName());
        patient.setEmail(patientdto.getEmail());
        patient.setLastName(patientdto.getLastName());
        patient.setAddress(patientdto.getAddress());
        patient.setGender(patientdto.getGender());
        patient.setAge(patientdto.getAge());
        patient.setPhoneNumber(patientdto.getPhoneNumber());

        Patient updated = patientRepository.save(patient);
        return PatientMapper.toDto(updated);
    }

    public void deletePatientById(Long id) {
        Patient Patient = patientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(("Patient not found with id:" + id)));
        patientRepository.delete(Patient);
    }

}