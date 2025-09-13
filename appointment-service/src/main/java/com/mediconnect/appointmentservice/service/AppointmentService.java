package com.mediconnect.appointmentservice.service;

import com.mediconnect.appointmentservice.appointmentdto.AppointmentRequestDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentSummaryDTO;
import com.mediconnect.appointmentservice.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

//    AppointmentResponseDTO saveAppointment(AppointmentRequestDTO appointmentRequestDTO);

   AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO);
   List<AppointmentResponseDTO> getAllAppointments();
   AppointmentResponseDTO getAppointmentById(Long id);
   List<AppointmentResponseDTO> getAppointmentsByPatientId(Long patientId);
   List<AppointmentResponseDTO> getAppointmentsByDoctorId(Long doctorId);
   List<AppointmentResponseDTO> getAppointmentByStatus(String status);
   AppointmentResponseDTO updateAppointment(Long id,AppointmentRequestDTO appointmentRequestDTO);
   void  deleteAppointmentById(Long id);
   AppointmentSummaryDTO getAppointmentSummaryById(Long id);
   List<AppointmentResponseDTO> getAppointmentByDateRange(LocalDateTime start, LocalDateTime end);

}
