package com.mediconnect.doctorservice.doctordto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;


    @NotBlank(message = "Name is mandatory")
    @Size(min = 1,max = 20,message = "Name should be between 1 and 20 characters")
    private String name;

    @NotBlank(message = "Specialization is mandatory")
    private String specialization;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;
}
