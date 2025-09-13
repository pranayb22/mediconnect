package com.mediconnect.patientservice.Service;

import com.mediconnect.patientservice.Entity.Patient;
import com.mediconnect.patientservice.Exception.PatientAlreadyExistException;
import com.mediconnect.patientservice.Exception.PatientNotFoundException;
import com.mediconnect.patientservice.Repository.PatientRepository;
import com.mediconnect.patientservice.mapper.PatientMappers;
import com.mediconnect.patientservice.patientdto.PatientRequestDTO;
import com.mediconnect.patientservice.patientdto.PatientResponseDTO;
import com.mediconnect.patientservice.patientdto.PatientSummaryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;
    private final PatientMappers patientMappers;

    //Create Logic
    @Transactional
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new PatientAlreadyExistException("Patient with email already exists");
        }
        if(patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())){
            throw new PatientAlreadyExistException("Patient with phone number already exists");
        }

        Patient patient = patientMappers.toEntity(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMappers.toResponse(savedPatient);
    }



    //Update Logic
    @Transactional
    public PatientResponseDTO updatePatient(Long id,PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found with id: " + id));


    if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
        throw new PatientAlreadyExistException("Patient with email already exists");
    }
    if(patientRepository.existsByPhoneNumberAndIdNot(patientRequestDTO.getPhoneNumber(),id)){
        throw new PatientAlreadyExistException("Patient with phone number already exists");
    }

    patient.setFirstName(patientRequestDTO.getFirstName());
    patient.setLastName(patientRequestDTO.getLastName());
    patient.setEmail(patientRequestDTO.getEmail());
    patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
    patient.setAddress(patientRequestDTO.getAddress());
    patient.setGender(patientRequestDTO.getGender());
    patient.setAge(patientRequestDTO.getAge());

    Patient savedPatient = patientRepository.save(patient);
    return patientMappers.toResponse(savedPatient);
    }


    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return  patientMappers.toResponseList(patients);
    }


    public PatientResponseDTO getPatientById(Long id) {
       return patientRepository.findById(id)
               .map(patientMappers :: toResponse)
               .orElseThrow(()-> new PatientNotFoundException("Patient not found with id: " + id));
    }


    public PatientSummaryDTO getPatientSummary(Long id) {
      Patient  patient = patientRepository.findById(id)
              .orElseThrow(()-> new PatientNotFoundException("Patient not found with id: " + id));
      return patientMappers.toSummary(patient);
    }


    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(patient);
    }

    public PatientResponseDTO getPatientByEmail(String email) {
        Patient patient = patientRepository.getPatientByEmail(email)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found with email: " + email));
        return patientMappers.toResponse(patient);
    }

}