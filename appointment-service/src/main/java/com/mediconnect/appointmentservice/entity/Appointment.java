package com.mediconnect.appointmentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

  @Column(nullable = false)
    private Long doctorId;

   @Column(nullable = false)
    private LocalDateTime appointmentDate;


    private String appointmentTime;

    @Column(nullable = false,length = 100)
    private String appointmentDescription;

    @Column(nullable = false,length = 20)
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
