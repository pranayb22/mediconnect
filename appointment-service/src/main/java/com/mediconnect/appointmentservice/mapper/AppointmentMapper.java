package com.mediconnect.appointmentservice.mapper;

import com.mediconnect.appointmentservice.appointmentdto.AppointmentRequestDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentResponseDTO;
import com.mediconnect.appointmentservice.appointmentdto.AppointmentSummaryDTO;
import com.mediconnect.appointmentservice.entity.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO);
    AppointmentResponseDTO toResponse(Appointment entity);
    AppointmentSummaryDTO toSummary(Appointment entity);
    List<AppointmentResponseDTO> toResponseList(List<Appointment> entities);
}
