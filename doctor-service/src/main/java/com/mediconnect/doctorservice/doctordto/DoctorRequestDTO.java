package com.mediconnect.doctorservice.doctordto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorRequestDTO {


    @NotBlank(message = "Name is Mandatory")
    @Size(min = 1,max = 50,message = "Name should be between 1 and 50 characters")
    private String doctorName;

    @NotBlank(message = "Email is Mandatory")
    @Email(message = "Email should be valid")
    private String doctorEmail;

    @NotBlank(message = "Phone is Mandatory")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String doctorPhone;

    @NotBlank(message = "Specialization is Mandatory")
    private String doctorSpecialization;
}
