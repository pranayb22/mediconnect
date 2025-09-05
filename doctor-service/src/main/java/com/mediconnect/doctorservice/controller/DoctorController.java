package com.mediconnect.doctorservice.controller;

import com.mediconnect.doctorservice.doctordto.DoctorDto;
import com.mediconnect.doctorservice.doctorservice.DoctorService;
import com.mediconnect.doctorservice.entity.Doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //Get all Doctors
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }


    //Get Doctors by id
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable @Positive Long id) {
        DoctorDto doctorDto = doctorService.getDoctorById(id);
        return new ResponseEntity<>(doctorDto, HttpStatus.OK);
    }


    //CREATE Doctor
    @PostMapping
    public ResponseEntity<DoctorDto> saveDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto saved =  doctorService.saveDoctor(doctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



    //Update Doctor
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDto doctorDto) {
       return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

}
