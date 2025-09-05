package com.mediconnect.appointmentservice.service;

import com.mediconnect.appointmentservice.appointmentdto.AppointmentDto;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDto;
import com.mediconnect.appointmentservice.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    AppointmentResponseDto saveAppointment(AppointmentDto appointmentDto);

    AppointmentResponseDto updateAppointment(Long id ,AppointmentDto appointmentDto);

    void deleteAppointment(Long id);

    List<AppointmentResponseDto> getAllAppointments();

    AppointmentResponseDto getAppointmentById(Long id);

    List<AppointmentResponseDto> getAppointmentByDoctorId(Long doctorId);
    List<AppointmentResponseDto> getAppointmentByPatientId(Long patientId);
    List<AppointmentResponseDto> getAppointmentByStatus(String status);
    List<AppointmentResponseDto> getAppointmentByDate(LocalDateTime Appointmentdate);


}
