package com.mediconnect.appointmentservice.appointmentdto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequestDTO {

    @NotNull(message = "Patient Id is required")
    private Long patientId;

    @NotNull(message = "Doctor Id is required")
    private Long doctorId;


    @NotNull(message = "Appointment Date is Required")
    private LocalDateTime appointmentDate;

    private String appointmentTime;

    @NotBlank(message = "Description is Required")
    @Size(min = 5, max = 100,message = "Description must be between 5 and 100 Characters")
    private String appointmentDescription;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED" ,message = "Status Must be Pending,Confirmed or Rejected")
    private String status;


}
