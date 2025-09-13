package com.mediconnect.patientservice.Repository;

import com.mediconnect.patientservice.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email,Long id);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber,Long id);
    Optional<Patient> getPatientByEmail(String email);

}
