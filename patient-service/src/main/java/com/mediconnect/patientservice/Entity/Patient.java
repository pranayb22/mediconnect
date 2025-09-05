package com.mediconnect.patientservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Enter your First Name")
    private String firstName;

    @NotBlank(message = "Enter Your Last Name")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Size(min = 10, max = 10,message = "It should be between 10 to 10 only")
    private String phoneNumber;


    private String address;
    private String gender;
    private Integer age;
}
