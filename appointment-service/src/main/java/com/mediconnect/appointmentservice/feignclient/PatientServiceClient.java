package com.mediconnect.appointmentservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service",url = "http://localhost:8082")
public interface PatientServiceClient {

    @GetMapping("/api/patients/{id}/exists")
    boolean checkPatientExists(@PathVariable Long id);

    @GetMapping("/api/patients/{id}")
    Object getPatientById(@PathVariable Long id);
}
