package com.mediconnect.appointmentservice.repository;

import com.mediconnect.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByStatus(String status);

    boolean existsByPatientIdAndDoctorIdAndAppointmentDate(
            Long patientId,
            Long doctorId,
            LocalDateTime appointmentDate
    );

    List<Appointment> findByAppointmentDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    boolean existsByPatientIdAndDoctorIdAndAppointmentDateAndIdNot(
            Long patientId,
            Long doctorId,
            LocalDateTime appointmentDate,
            Long id
    );



    @Query("SELECT a.appointmentTime FROM Appointment a WHERE a.doctorId = :doctorId AND DATE(a.appointmentDate) = :date")
    List<String> findBookedSlotsByDoctorAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);
}
