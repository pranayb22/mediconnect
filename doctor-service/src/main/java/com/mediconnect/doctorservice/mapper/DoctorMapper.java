package com.mediconnect.doctorservice.mapper;

import com.mediconnect.doctorservice.doctordto.DoctorDto;
import com.mediconnect.doctorservice.entity.Doctor;

public class DoctorMapper {

    public static DoctorDto toDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setPhone(doctor.getPhone());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setSpecialization( doctor.getSpecialization() );
        return doctorDto;
    }

    public static Doctor toEntity(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
//        doctor.setId(doctorDto.getId());
        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setSpecialization(doctorDto.getSpecialization());
        return doctor;
    }
}
