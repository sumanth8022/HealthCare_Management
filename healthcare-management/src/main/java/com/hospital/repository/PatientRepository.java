package com.hospital.repository;

import com.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByNameContainingIgnoreCase(String name);

    List<Patient> findByStatus(Patient.PatientStatus status);

    @Query("SELECT p FROM Patient p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(p.disease) LIKE LOWER(CONCAT('%',:keyword,'%')) OR p.phone LIKE CONCAT('%',:keyword,'%')")
    List<Patient> searchPatients(@Param("keyword") String keyword);

    long countByStatus(Patient.PatientStatus status);
}
