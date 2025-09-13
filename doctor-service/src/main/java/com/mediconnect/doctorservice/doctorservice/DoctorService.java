package com.mediconnect.doctorservice.doctorservice;

import com.mediconnect.doctorservice.doctordto.DoctorRequestDTO;
import com.mediconnect.doctorservice.doctordto.DoctorResponseDTO;
import com.mediconnect.doctorservice.doctordto.DoctorSummaryDTO;
import com.mediconnect.doctorservice.entity.Doctor;
import com.mediconnect.doctorservice.exception.DoctorAlreadyExistsException;
import com.mediconnect.doctorservice.exception.DoctorNotFoundException;
import com.mediconnect.doctorservice.mapper.DoctorMappers;
import com.mediconnect.doctorservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMappers doctorMappers;

    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        if (doctorRepository.existsByDoctorEmail((doctorRequestDTO.getDoctorEmail()))){
            throw new DoctorAlreadyExistsException("Email already exists");
        }

        if (doctorRepository.existsByDoctorPhone((doctorRequestDTO.getDoctorPhone()))){
            throw new DoctorAlreadyExistsException("Phone already exists");
        }

        Doctor saved = doctorMappers.toEntity(doctorRequestDTO);
        Doctor persisted = doctorRepository.save(saved);
        return doctorMappers.toResponse(persisted);
    }

    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorMappers.toResponseList(doctorRepository.findAll());
    }

    public DoctorSummaryDTO getDoctorSummary(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id " + id));
        return doctorMappers.toSummary(doctor);
    }

    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id " + id));
        return doctorMappers.toResponse(doctor);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id " + id));
        doctorRepository.delete(doctor);
    }

    @Transactional
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id " + id));

        // Check for duplicate email (excluding current doctor)
        if (doctorRepository.existsByDoctorEmailAndIdNot(doctorRequestDTO.getDoctorEmail(), id)) {
            throw new DoctorAlreadyExistsException("Email already exists");
        }

        // Check for duplicate phone (excluding current doctor)
        if (doctorRepository.existsByDoctorPhoneAndIdNot(doctorRequestDTO.getDoctorPhone(), id)) {
            throw new DoctorAlreadyExistsException("Phone already exists");
        }

        doctor.setDoctorName(doctorRequestDTO.getDoctorName());
        doctor.setDoctorEmail(doctorRequestDTO.getDoctorEmail());
        doctor.setDoctorPhone(doctorRequestDTO.getDoctorPhone());
        doctor.setDoctorSpecialization(doctorRequestDTO.getDoctorSpecialization());

        Doctor saved = doctorRepository.save(doctor);
        return doctorMappers.toResponse(saved);
    }
}