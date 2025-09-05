package com.mediconnect.appointmentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Patient id is required")
    private Long patientId;

    @NotNull(message = "Doctor id is required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be in future or present ")
    private LocalDateTime appointmentDate;

    private String appointmentTime;

    @NotNull(message = "Enter Description in short")
    @Size(min = 5,max = 20,message = "Description must be in 5 to 20 characters ")
    private String appointmentDescription;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED",message = "status must be pending ,confirmed or cancelled")
    private String status;


}
