package com.mediconnect.doctorservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Arrays;
import java.util.List;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



   @Column(name = "doctor_name", nullable = false,length = 50)
    private String doctorName;


    @Column(name = "doctor_specialization",nullable = false,length = 100)
    private String doctorSpecialization;


    @Email
    @Column(name = "doctor_email", unique = true, nullable = false,length = 100)
    private String doctorEmail;


    @Column(name = "doctor_phone", nullable = false,length = 15)
    private String doctorPhone;


    //For Time slots

    @ElementCollection
    @CollectionTable(name = "doctor_availabilty",joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "time_slot")
    private List<String> availableTimeSlots = Arrays.asList(
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"
    );

}
