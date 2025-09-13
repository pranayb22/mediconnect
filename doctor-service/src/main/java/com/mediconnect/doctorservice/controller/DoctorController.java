package com.mediconnect.doctorservice.controller;

import com.mediconnect.doctorservice.doctordto.DoctorRequestDTO;
import com.mediconnect.doctorservice.doctordto.DoctorResponseDTO;
import com.mediconnect.doctorservice.doctordto.DoctorSummaryDTO;
import com.mediconnect.doctorservice.doctorservice.DoctorService;
import com.mediconnect.doctorservice.doctorservice.SlotService;
import com.mediconnect.doctorservice.repository.DoctorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final SlotService slotService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        DoctorResponseDTO response = doctorService.createDoctor(doctorRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<DoctorSummaryDTO> getDoctorSummary(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorSummary(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Doctor has been deleted");
        response.put("status", "success");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorRequestDTO));
    }



    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkDoctorExists(@PathVariable Long id){
        boolean exists = doctorRepository.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        DoctorResponseDTO doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }


    @GetMapping("/{doctorId}/slots")
    public ResponseEntity<List<String>> getAvailableTimeSlots(@PathVariable Long doctorId,@PathVariable  String date) {
        List<String> availableSlots = slotService.getAvailableTimeSlots(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }


    @GetMapping("/{doctorId}/slots/{date}")
    public ResponseEntity<List<String>> getAvailableSlots(@PathVariable Long doctorId,@PathVariable String date) {

        List<String> availableSlots = slotService.getAvailableTimeSlots(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }

}