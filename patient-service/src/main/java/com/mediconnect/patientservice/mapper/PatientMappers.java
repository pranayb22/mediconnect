package com.mediconnect.patientservice.mapper;

import com.mediconnect.patientservice.Entity.Patient;
import com.mediconnect.patientservice.patientdto.PatientRequestDTO;
import com.mediconnect.patientservice.patientdto.PatientResponseDTO;
import com.mediconnect.patientservice.patientdto.PatientSummaryDTO;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMappers {

    Patient toEntity(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO toResponse(Patient entity);
    PatientSummaryDTO toSummary(Patient entity);
    List<PatientResponseDTO> toResponseList(List<Patient> entity);
}
