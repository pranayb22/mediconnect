package com.mediconnect.patientservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;



    @Column(name = "patient_firstname", nullable = false, length = 50)
    private String firstName;


    @Column(name = "patient_lastname", nullable = false, length = 50)
    private String lastName;

    @Column(name = "patient_email", nullable = false, length = 100)
    private String email;

    @Column(name = "patient_phone", nullable = false, length = 15)
    private String phoneNumber;


    @Column(name = "patient_address", nullable = false, length = 100)
    private String address;

    @Column(name = "patient_gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "patient_age", nullable = false)
    private Integer age;
}
