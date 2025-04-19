package com.davidnguyen.patientservice.service;

import com.davidnguyen.patientservice.dto.PatientRequestDTO;
import com.davidnguyen.patientservice.dto.PatientResponseDTO;
import com.davidnguyen.patientservice.entity.Patient;
import com.davidnguyen.patientservice.exception.EmailAlreadyExistsException;
import com.davidnguyen.patientservice.grpc.BillingServiceGrpcClient;
import com.davidnguyen.patientservice.repository.PatientRepository;
import mapper.PatientMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository,
                          BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    String.format("A patient with this email %s already exists",
                            patientRequestDTO.getEmail()));
        }

        Patient newPatient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));

        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),
                newPatient.getName(),
                newPatient.getEmail());

        return PatientMapper.toDTO(newPatient);

    }
}
