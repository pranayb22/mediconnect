package com.mediconnect.doctorservice.repository;

import com.mediconnect.doctorservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    boolean existsByDoctorEmailAndIdNot(String doctorEmail, Long id);
    boolean existsByDoctorPhoneAndIdNot(String doctorPhone, Long id);

    boolean existsByDoctorEmail(String doctorEmail);

    boolean existsByDoctorPhone(String doctorPhone);
}
