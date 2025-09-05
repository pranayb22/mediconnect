package com.mediconnect.appointmentservice.mapper;

import com.mediconnect.appointmentservice.appointmentdto.AppointmentDto;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDto;
import com.mediconnect.appointmentservice.entity.Appointment;

public class AppointmentMapper {
    public static Appointment toEntity(AppointmentDto appointmentdto) {
        return Appointment.builder()
                .patientId(appointmentdto.getPatientId())
                .doctorId(appointmentdto.getDoctorId())
                .appointmentDate(appointmentdto.getAppointmentDate())
                .appointmentTime(appointmentdto.getAppointmentTime())
                .appointmentDescription(appointmentdto.getAppointmentDescription())
                .status(appointmentdto.getStatus())
                .build();
    }


    public static AppointmentResponseDto toDto(Appointment appointmentEntity) {
        return AppointmentResponseDto.builder()
                .id(appointmentEntity.getId())
                .patientId(appointmentEntity.getPatientId())
                .doctorId(appointmentEntity.getDoctorId())
                .appointmentDate(appointmentEntity.getAppointmentDate())
                .appointmentTime(appointmentEntity.getAppointmentTime())
                .appointmentDescription(appointmentEntity.getAppointmentDescription())
                .status(appointmentEntity.getStatus())
                .build();
    }
}
