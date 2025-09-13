package com.mediconnect.appointmentservice.appointmentdto;

import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class AppointmentResponseDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String appointmentTime;
    private String appointmentDescription;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
