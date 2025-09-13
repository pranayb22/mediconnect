package com.mediconnect.doctorservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "appointment-service",url = "http://localhost:8083")
public interface AppointmentServiceClient {

    @GetMapping("/api/appointments/doctor/{doctorId}/booked-slots/{date}")
    List<String> getBookedSlots(@PathVariable Long doctorId, @PathVariable String date);
}
