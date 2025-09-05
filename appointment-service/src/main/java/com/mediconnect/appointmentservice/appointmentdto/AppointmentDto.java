package com.mediconnect.appointmentservice.appointmentdto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.function.LongFunction;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class AppointmentDto {


    @NotNull(message = "PatientId is required")
    private Long patientId;

    @NotNull(message = "DoctorId is required")
    private Long doctorId;

    @NotNull(message = "Appointment Date is required ")
    @FutureOrPresent(message = "Appointment Date must be in future or present")
    private LocalDateTime appointmentDate;

    private String appointmentTime;

    @NotNull(message = "Enter Description in Short")
    @Size(min = 5,max = 15, message = "Description should be between 5 and 15 Characters")
    private String appointmentDescription;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED",message = "Status must be Pending,Confirmed or Cancelled")
    private String status;


}
