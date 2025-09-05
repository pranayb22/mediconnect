package com.mediconnect.patientservice.patientdto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {


    private Long id;

    @NotBlank(message = "Name is Mandatory")
    private String firstName;

    @NotBlank(message = "Name is Mandatory")
    private String lastName;

    @NotBlank(message = "Must enter the Gender")
    private String gender;

    @Column(unique = true)
    private String Email;

    private String address;
    private Integer age;

    @Column(unique = true)
    private String phoneNumber;

}


