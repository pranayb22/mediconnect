package com.mediconnect.patientservice.Controller;


import com.mediconnect.patientservice.repository.PatientRepository;
import com.mediconnect.patientservice.Service.PatientService;
import com.mediconnect.patientservice.patientdto.PatientRequestDTO;
import com.mediconnect.patientservice.patientdto.PatientResponseDTO;
import com.mediconnect.patientservice.patientdto.PatientSummaryDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.CREATED);
    }

@GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

//@GetMapping("/{id}")
//    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id){
//        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
//    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<PatientSummaryDTO> getPatientSummaryById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientSummary(id));
    }

@PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.updatePatient(id,patientRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok(Map.of(
                "message" ,"Patient has been deleted successfully",
                "status","success",
                "id",id
        ));
    }


    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkPatientExists(@PathVariable  Long id ){
        boolean exists = patientRepository.existsById(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientsById(@PathVariable Long id){
        PatientResponseDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }




}
