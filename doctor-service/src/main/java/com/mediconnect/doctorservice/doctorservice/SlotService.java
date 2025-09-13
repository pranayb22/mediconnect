package com.mediconnect.doctorservice.doctorservice;

import com.mediconnect.doctorservice.feignclient.AppointmentServiceClient;
import com.mediconnect.doctorservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotService {

   private final DoctorRepository doctorRepository;
    private final AppointmentServiceClient appointmentServiceClient;


    public List<String> getAvailableTimeSlots(Long doctorId,String date) {
      List<String> allSlots = doctorRepository.findById(doctorId)
              .orElseThrow(()->new RuntimeException("Doctor Not Found"))
              .getAvailableTimeSlots();



        // Get booked slots from Appointment Service via Feign
        List<String> bookedSlots = appointmentServiceClient.getBookedSlots(doctorId, date);

        // Return available slots (remove booked slots)
        return allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .collect(Collectors.toList());
    }
}
