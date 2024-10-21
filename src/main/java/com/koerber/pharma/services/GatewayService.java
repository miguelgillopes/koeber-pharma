package com.koerber.pharma.services;

import com.koerber.pharma.dto.Patient;
import com.koerber.pharma.infrastructure.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

    private final PatientRepository patientRepository;


    public GatewayService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Page<Patient> getPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findAll(pageable);
    }
}
