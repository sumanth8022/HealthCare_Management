package com.hospital.repository;

import com.hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByAppointmentDate(LocalDate date);

    List<Appointment> findByStatus(Appointment.AppointmentStatus status);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.doctor.id = :doctorId")
    List<Appointment> findByDateAndDoctor(LocalDate date, Long doctorId);

    long countByStatus(Appointment.AppointmentStatus status);

    @Query("SELECT a FROM Appointment a ORDER BY a.appointmentDate DESC, a.appointmentTime DESC")
    List<Appointment> findAllOrderByDateDesc();
}
