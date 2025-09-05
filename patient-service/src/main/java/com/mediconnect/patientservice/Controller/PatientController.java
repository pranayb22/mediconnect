package com.mediconnect.patientservice.Controller;

import com.mediconnect.patientservice.Entity.Patient;
import com.mediconnect.patientservice.Service.PatientService;
import com.mediconnect.patientservice.patientdto.PatientDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService  ) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable @Positive Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PatientDto> savePatient(@Valid @RequestBody PatientDto patientdto) {
        PatientDto saved = patientService.savePatient(patientdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientdto) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id,patientdto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) {
       patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }

}
