package com.mediconnect.appointmentservice.controller;


import com.mediconnect.appointmentservice.appointmentdto.AppointmentRequestDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentSummaryDTO;
import com.mediconnect.appointmentservice.repository.AppointmentRepository;
import com.mediconnect.appointmentservice.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;


    //create
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentResponseDTO created = appointmentService.createAppointment(appointmentRequestDTO);
        return new  ResponseEntity<>(created,HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id , @Valid @RequestBody AppointmentRequestDTO updatedAppointmentdto) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id,updatedAppointmentdto));
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>>  deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
        return  ResponseEntity.ok(
                Map.of("message","Appointment has been deleted successfully",
                        "status","success",
                        "id" ,id)
        );
    }

    //Fetch all
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDTO appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);

    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointmentsByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByStatus(@PathVariable String status){
        return ResponseEntity.ok(appointmentService.getAppointmentByStatus(status));
    }

@GetMapping("/summary/{id}")
    public ResponseEntity<AppointmentSummaryDTO> getAppointmentSummary(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.getAppointmentSummaryById(id));
    }


    @GetMapping("/date-range")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDateRange(@RequestParam LocalDateTime start,@RequestParam LocalDateTime end){
return ResponseEntity.ok(appointmentService.getAppointmentByDateRange(start, end));
    }



    @GetMapping("/doctor/{doctorId}/booked-slots/{date}")
    public ResponseEntity<List<String>> getBookedSlots(@PathVariable Long doctorId,@PathVariable String date){

        System.out.println("Received date string: '" + date + "'");
        System.out.println("Length: " + date.length());
        try {
            LocalDate localDate = LocalDate.parse(date);
            System.out.println("Successfully parsed as: " + localDate);
            List<String> bookedSlots = appointmentRepository.findBookedSlotsByDoctorAndDate(doctorId, localDate);
            return ResponseEntity.ok(bookedSlots);
        }
        catch (Exception e){
            System.out.println("Parse error: " + e.getMessage());
            throw new RuntimeException("Invalid date format. Use YYYY-MM-DD format: " + date);
        }
    }
}
