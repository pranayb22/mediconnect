package com.mediconnect.appointmentservice.controller;


import com.mediconnect.appointmentservice.appointmentdto.AppointmentDto;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDto;
import com.mediconnect.appointmentservice.entity.Appointment;
import com.mediconnect.appointmentservice.exception.AppointmentNotFoundException;
import com.mediconnect.appointmentservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //create
    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
        AppointmentResponseDto created = appointmentService.saveAppointment(appointmentDto);
        return new  ResponseEntity<>(created,HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id ,@Valid @RequestBody AppointmentDto updatedAppointmentdto) {
        AppointmentResponseDto updated = appointmentService.updateAppointment(id, updatedAppointmentdto);
        return new  ResponseEntity<>(updated,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return  ResponseEntity.noContent().build();
    }

    //Fetch all
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);

    }

    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatientId(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentByPatientId(id));
    }


    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsByDoctorId(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentByDoctorId(id));
    }

    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDate(@PathVariable String appointmentDate){
        LocalDateTime date = LocalDateTime.parse(appointmentDate);
        return ResponseEntity.ok(appointmentService.getAppointmentByDate(date));
    }

    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByStatus(@PathVariable String appointmentStatus){
        return ResponseEntity.ok(appointmentService.getAppointmentByStatus(appointmentStatus));
    }
}
