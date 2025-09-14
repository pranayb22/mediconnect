package com.mediconnect.patientservice.patientdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String email;
    private String password;
    private String role;
    private Long patientId;
}
