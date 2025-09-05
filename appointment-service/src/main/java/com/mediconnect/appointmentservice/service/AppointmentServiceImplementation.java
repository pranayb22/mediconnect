package com.mediconnect.appointmentservice.service;

import com.mediconnect.appointmentservice.appointmentdto.AppointmentDto;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDto;
import com.mediconnect.appointmentservice.entity.Appointment;
import com.mediconnect.appointmentservice.exception.AppointmentNotFoundException;
import com.mediconnect.appointmentservice.mapper.AppointmentMapper;
import com.mediconnect.appointmentservice.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

private final AppointmentRepository appointmentRepository;

public AppointmentServiceImplementation(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
}

//Create
@Override
    public AppointmentResponseDto saveAppointment(AppointmentDto appointmentDto) {
    Appointment appointment = AppointmentMapper.toEntity(appointmentDto);
    Appointment savedAppointment = appointmentRepository.save(appointment);
    return AppointmentMapper.toDto(savedAppointment);
    }


    //Update
    @Override
    public AppointmentResponseDto updateAppointment(Long id,AppointmentDto appointmentDto) {

    Appointment existing = appointmentRepository.findById(id)
            .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id " + id));

        //Update necessary fields
        existing.setStatus(appointmentDto.getStatus());
        existing.setAppointmentDate(appointmentDto.getAppointmentDate());
        existing.setAppointmentTime(appointmentDto.getAppointmentTime());
        existing.setPatientId(appointmentDto.getPatientId());
        existing.setDoctorId(appointmentDto.getDoctorId());

        Appointment  updatedAppointment = appointmentRepository.save(existing);
        return AppointmentMapper.toDto(updatedAppointment);
    }

    //Delete
    @Override
    public void deleteAppointment(Long id) {
    if(!appointmentRepository.existsById(id)) {
        throw new AppointmentNotFoundException("Appointment not found with id " + id);
    }
    appointmentRepository.deleteById(id);
    }

    //Fetch all by id
    @Override
   public AppointmentResponseDto getAppointmentById(Long id){
    Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(()->new AppointmentNotFoundException("Appointment not found by Id" +id));
    return AppointmentMapper.toDto(appointment);
    }

    //Fetch all
    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
    return appointmentRepository.findAll()
            .stream()
            .map(AppointmentMapper :: toDto)
            .collect(Collectors.toList());
    }

    //Fetch by doctorId
    @Override
    public List<AppointmentResponseDto> getAppointmentByDoctorId(Long doctorId) {
    return appointmentRepository.findByDoctorId(doctorId)
            .stream()
            .map(AppointmentMapper ::toDto)
            .collect(Collectors.toList());
    }

    //Fetch by Patient Id
    @Override
    public List<AppointmentResponseDto> getAppointmentByPatientId(Long patientId) {
    return appointmentRepository.findByPatientId(patientId)
            .stream()
            .map(AppointmentMapper ::toDto)
            .collect(Collectors.toList());
    }

    //Fetch by Date
    @Override
    public List<AppointmentResponseDto> getAppointmentByDate(LocalDateTime appointmentdate) {
    return appointmentRepository.findByAppointmentDate(appointmentdate)
            .stream()
            .map(AppointmentMapper ::toDto)
            .collect(Collectors.toList());
    }

    //Fetch by status
    @Override
    public List<AppointmentResponseDto> getAppointmentByStatus(String status) {
    return appointmentRepository.findByStatus(status)
            .stream()
            .map(AppointmentMapper ::toDto)
            .collect(Collectors.toList());
    }
}
