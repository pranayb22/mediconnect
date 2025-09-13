package com.mediconnect.doctorservice.doctordto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {

    private Long id;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorEmail;
    private String doctorPhone;
}
