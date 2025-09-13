package com.mediconnect.patientservice.patientdto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {

    @NotBlank(message = "First Name is Mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is Mandatory")
    private String lastName;

    @NotBlank(message = "Email is Mandatory")
    @Email(message = "Email should be Valid")
    private String email;


    @NotBlank(message = "Phone Number is Mandatory")
    @Pattern(regexp ="\\d{10}", message = "Phone number must be 10 digits" )
    private String phoneNumber;

    @NotBlank(message = "Address is Mandatory")
    @Size(min = 10, max = 40,message = "Address should be between 10 to 40 Characters")
    private String address;


    @NotBlank(message = "Gender is Mandatory")
    private String gender;

    @Positive(message = "Age must be positive")
    @NotNull(message = "Age is Mandatory")
    private Integer age;



}
