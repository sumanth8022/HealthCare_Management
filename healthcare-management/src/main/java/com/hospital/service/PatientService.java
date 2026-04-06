package com.hospital.service;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import com.hospital.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setGender(patientDetails.getGender());
        patient.setPhone(patientDetails.getPhone());
        patient.setEmail(patientDetails.getEmail());
        patient.setAddress(patientDetails.getAddress());
        patient.setDisease(patientDetails.getDisease());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setStatus(patientDetails.getStatus());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }

    public void cancelRegistration(Long id) {
        Patient patient = getPatientById(id);
        patient.setStatus(Patient.PatientStatus.CANCELLED);
        patientRepository.save(patient);
    }

    public List<Patient> searchPatients(String keyword) {
        return patientRepository.searchPatients(keyword);
    }

    public List<Patient> getActivePatients() {
        return patientRepository.findByStatus(Patient.PatientStatus.ACTIVE);
    }

    public long getActiveCount() {
        return patientRepository.countByStatus(Patient.PatientStatus.ACTIVE);
    }

    public long getTotalCount() {
        return patientRepository.count();
    }
}
