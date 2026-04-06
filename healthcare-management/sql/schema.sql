-- Healthcare Management System Database Schema
-- Run this script to create the database and tables

CREATE DATABASE IF NOT EXISTS healthcare_db;
USE healthcare_db;

-- Patients Table
CREATE TABLE IF NOT EXISTS patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    address TEXT,
    disease VARCHAR(200) NOT NULL,
    blood_group VARCHAR(5),
    status ENUM('ACTIVE', 'INACTIVE', 'CANCELLED') DEFAULT 'ACTIVE',
    registration_date DATE DEFAULT (CURRENT_DATE),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_patient_name (name),
    INDEX idx_patient_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Doctors Table
CREATE TABLE IF NOT EXISTS doctors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    qualification VARCHAR(100),
    experience INT DEFAULT 0,
    consultation_fee DECIMAL(10,2) DEFAULT 0.00,
    status ENUM('AVAILABLE', 'ON_LEAVE', 'UNAVAILABLE') DEFAULT 'AVAILABLE',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_doctor_specialization (specialization),
    INDEX idx_doctor_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Appointments Table
CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    reason TEXT,
    notes TEXT,
    status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED', 'NO_SHOW') DEFAULT 'SCHEDULED',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
    INDEX idx_appointment_date (appointment_date),
    INDEX idx_appointment_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample Data
INSERT INTO doctors (name, specialization, phone, email, qualification, experience, consultation_fee) VALUES
('Dr. Sarah Johnson', 'Cardiology', '555-0101', 'sarah.johnson@hospital.com', 'MBBS, MD Cardiology', 12, 150.00),
('Dr. Michael Chen', 'Neurology', '555-0102', 'michael.chen@hospital.com', 'MBBS, DM Neurology', 8, 200.00),
('Dr. Emily Davis', 'Pediatrics', '555-0103', 'emily.davis@hospital.com', 'MBBS, MD Pediatrics', 6, 100.00),
('Dr. James Wilson', 'Orthopedics', '555-0104', 'james.wilson@hospital.com', 'MBBS, MS Orthopedics', 15, 175.00),
('Dr. Lisa Brown', 'General Medicine', '555-0105', 'lisa.brown@hospital.com', 'MBBS', 5, 80.00);

INSERT INTO patients (name, age, gender, phone, email, disease, blood_group) VALUES
('John Smith', 45, 'Male', '555-1001', 'john@email.com', 'Hypertension', 'A+'),
('Maria Garcia', 32, 'Female', '555-1002', 'maria@email.com', 'Diabetes Type 2', 'B+'),
('Robert Lee', 58, 'Male', '555-1003', 'robert@email.com', 'Arthritis', 'O+'),
('Anna White', 28, 'Female', '555-1004', 'anna@email.com', 'Migraine', 'AB-'),
('David Brown', 67, 'Male', '555-1005', 'david@email.com', 'Heart Disease', 'A-');

INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason, status) VALUES
(1, 1, CURDATE(), '09:00:00', 'Blood pressure check', 'SCHEDULED'),
(2, 5, CURDATE(), '10:30:00', 'Routine checkup', 'SCHEDULED'),
(3, 4, CURDATE(), '14:00:00', 'Joint pain consultation', 'SCHEDULED');
