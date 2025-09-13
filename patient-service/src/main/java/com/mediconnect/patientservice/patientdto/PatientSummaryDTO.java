package com.mediconnect.patientservice.patientdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientSummaryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String address;
}
