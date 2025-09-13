package com.mediconnect.appointmentservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "doctor-service",url = "http://localhost:8081")
public interface DoctorServiceClient {

    @GetMapping("/api/doctors/{id}/exists")
    boolean checkDoctorExists(@PathVariable Long id);

    @GetMapping("/api/doctors/{id}")
    Object getDoctorById(@PathVariable Long id);

@GetMapping("/api/doctors/{doctorId}/slots/{date}")
    List<String> getAvailableSlots(@PathVariable Long doctorId,@PathVariable String date);
}
