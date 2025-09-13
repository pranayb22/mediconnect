package com.mediconnect.appointmentservice.service;

import com.mediconnect.appointmentservice.appointmentdto.AppointmentRequestDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentSummaryDTO;
import com.mediconnect.appointmentservice.entity.Appointment;
import com.mediconnect.appointmentservice.exception.AppointmentAlreadyExistsException;
import com.mediconnect.appointmentservice.exception.AppointmentNotFoundException;
import com.mediconnect.appointmentservice.feignclient.DoctorServiceClient;
import com.mediconnect.appointmentservice.feignclient.PatientServiceClient;
import com.mediconnect.appointmentservice.mapper.AppointmentMapper;
import com.mediconnect.appointmentservice.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImplementation implements AppointmentService {

private final AppointmentRepository appointmentRepository;
private final AppointmentMapper appointmentMapper;
private final DoctorServiceClient doctorServiceClient;
private final PatientServiceClient patientServiceClient;

@Override
public AppointmentResponseDTO  createAppointment(AppointmentRequestDTO appointmentRequestDTO )
{
if(!doctorServiceClient.checkDoctorExists(appointmentRequestDTO.getDoctorId())){
    throw new RuntimeException("Doctor not found with id " + appointmentRequestDTO.getDoctorId());
}

    if(!patientServiceClient.checkPatientExists(appointmentRequestDTO.getPatientId())){
        throw new RuntimeException("Patient not found with id " + appointmentRequestDTO.getPatientId());
    }

    String appointmentDateStr = appointmentRequestDTO.getAppointmentDate().toLocalDate().toString();

    //CHECK TIME SLOT AVAILABILITY
    List<String> availableSlots = doctorServiceClient.getAvailableSlots(appointmentRequestDTO.getDoctorId(), appointmentDateStr);
    if(!availableSlots.contains(appointmentRequestDTO.getAppointmentTime())){
        throw new RuntimeException("Time slot not available .Available Time slots are :" +availableSlots);
    }


if(appointmentRepository.existsByPatientIdAndDoctorIdAndAppointmentDate(
        appointmentRequestDTO.getPatientId(),
        appointmentRequestDTO.getDoctorId(),
        appointmentRequestDTO.getAppointmentDate()
)){
    throw new AppointmentAlreadyExistsException("Appointment already exists for this patient and doctor at the given time");
}

Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO);
appointment.setCreatedAt(LocalDateTime.now());
appointment.setUpdatedAt(LocalDateTime.now());

Appointment savedAppointment = appointmentRepository.save(appointment);
return appointmentMapper.toResponse(savedAppointment);
}



@Override
@Transactional
public AppointmentResponseDTO updateAppointment(Long id,AppointmentRequestDTO appointmentRequestDTO)
{
    Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(()-> new AppointmentNotFoundException("Appointment not found with this id"));

    if(appointmentRepository.existsByPatientIdAndDoctorIdAndAppointmentDateAndIdNot(
            appointmentRequestDTO.getPatientId(),
            appointmentRequestDTO.getDoctorId(),
            appointmentRequestDTO.getAppointmentDate(),id
    )){
        throw new AppointmentAlreadyExistsException("Another appointment already exists for this patient and doctor at the given time");
    }

    appointment.setPatientId(appointmentRequestDTO.getPatientId());
    appointment.setDoctorId(appointmentRequestDTO.getDoctorId());
    appointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
    appointment.setAppointmentTime(appointmentRequestDTO.getAppointmentTime());
    appointment.setAppointmentDescription(appointmentRequestDTO.getAppointmentDescription());
    appointment.setStatus(appointmentRequestDTO.getStatus());
    appointment.setUpdatedAt(LocalDateTime.now());

    Appointment updatedAppointment = appointmentRepository.save(appointment);
    return appointmentMapper.toResponse(updatedAppointment);
}


@Override
public List<AppointmentResponseDTO> getAllAppointments()
{
    List<Appointment> appointments = appointmentRepository.findAll();
    return appointmentMapper.toResponseList(appointments);
}

@Override
public AppointmentResponseDTO getAppointmentById(Long id){
    Appointment appointment =appointmentRepository.findById(id)
            .orElseThrow(()-> new AppointmentNotFoundException("Appointment not found with this id"));
    return appointmentMapper.toResponse(appointment);

}

@Override
public List<AppointmentResponseDTO> getAppointmentsByPatientId(Long patientId){
    List<Appointment> appointment = appointmentRepository.findByPatientId(patientId);
    return  appointmentMapper.toResponseList(appointment);
}

@Override
public List<AppointmentResponseDTO> getAppointmentsByDoctorId(Long doctorId){
    List<Appointment> appointment = appointmentRepository.findByDoctorId(doctorId);
    return  appointmentMapper.toResponseList(appointment);
}

@Override
public List<AppointmentResponseDTO> getAppointmentByStatus(String status){
    List<Appointment> appointment = appointmentRepository.findByStatus(status);
    return  appointmentMapper.toResponseList(appointment);
}

@Override
@Transactional
public void deleteAppointmentById(Long id){
    Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(()-> new AppointmentNotFoundException("Appointment not found with this id"));
    appointmentRepository.delete(appointment);
}

@Override
public AppointmentSummaryDTO getAppointmentSummaryById(Long id){
    Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(()-> new AppointmentNotFoundException("Appointment not found with this id"));
    return  appointmentMapper.toSummary(appointment);
}

@Override
public List<AppointmentResponseDTO> getAppointmentByDateRange(LocalDateTime start, LocalDateTime end){
    List<Appointment> appointment = appointmentRepository.findByAppointmentDateBetween(start, end);
    return  appointmentMapper.toResponseList(appointment);
}
}
