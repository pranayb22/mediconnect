package com.mediconnect.doctorservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Name is mandatory")
    @Size(min = 2,max = 50,message = "Name must be between 2 and 50 Characters")
    private String name;

    @NotBlank(message = "Specialization is mandatory")
    private String specialization;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;


}
