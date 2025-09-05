package com.mediconnect.doctorservice.doctorservice;

import com.mediconnect.doctorservice.doctordto.DoctorDto;
import com.mediconnect.doctorservice.entity.Doctor;
import com.mediconnect.doctorservice.exception.ResourceNotFoundException;
import com.mediconnect.doctorservice.mapper.DoctorMapper;
import com.mediconnect.doctorservice.repository.DoctorRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {

        this.doctorRepository = doctorRepository;
    }


    public List<DoctorDto> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper ::toDto)
                .toList();
    }

    public DoctorDto getDoctorById(Long id){
    Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not Found with id " +id));
    return DoctorMapper.toDto(doctor);
    }

    public DoctorDto saveDoctor(DoctorDto doctorDto)
    {
        Doctor doctor = DoctorMapper.toEntity(doctorDto);
        Doctor saved = doctorRepository.save(doctor);
        return DoctorMapper.toDto(saved);
    }

    public DoctorDto updateDoctor(Long id,DoctorDto dto) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " +id));

        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialization(dto.getSpecialization() );
        doctor.setPhone(dto.getPhone());

        Doctor updated = doctorRepository.save(doctor);
        return DoctorMapper.toDto(updated);
    }

    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(("Doctor not found with id:" + id)));
        doctorRepository.delete(doctor);
    }
}
