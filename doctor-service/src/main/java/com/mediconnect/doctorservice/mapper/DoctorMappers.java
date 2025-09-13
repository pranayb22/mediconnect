package com.mediconnect.doctorservice.mapper;

import com.mediconnect.doctorservice.doctordto.DoctorRequestDTO;
import com.mediconnect.doctorservice.doctordto.DoctorResponseDTO;
import com.mediconnect.doctorservice.doctordto.DoctorSummaryDTO;
import com.mediconnect.doctorservice.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMappers {

//    @Mapping(target = "id", ignore = true)
    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);
    DoctorResponseDTO toResponse(Doctor entity);
    DoctorSummaryDTO toSummary(Doctor entity);
    List<DoctorResponseDTO> toResponseList(List<Doctor> entities);

}
