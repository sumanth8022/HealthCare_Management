# Healthcare Management System

A full-stack Healthcare Management System built with **Spring Boot**, **MySQL**, **Thymeleaf**, and **Bootstrap 5**.

## Features

- **Login Page** — Demo authentication
- **Admin Dashboard** — Stats overview with quick actions
- **Patient Management** — Register, edit, search, cancel, delete patients
- **Doctor Management** — Add, edit, delete doctors with specializations
- **Appointment Booking** — Schedule, complete, cancel appointments
- **Appointment History** — View patient appointment history
- **Search** — Search patients by name, disease, or phone
- **Responsive UI** — Bootstrap 5 with sidebar navigation

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Spring Boot 3.2.5 |
| Database | MySQL 8.x |
| ORM | JPA / Hibernate |
| Frontend | Thymeleaf + Bootstrap 5 |
| Build | Maven |
| Java | 17+ |

## Project Structure

```
src/main/java/com/hospital/
├── HealthcareApplication.java
├── controller/
│   ├── HomeController.java
│   ├── PatientController.java
│   ├── DoctorController.java
│   └── AppointmentController.java
├── service/
│   ├── PatientService.java
│   ├── DoctorService.java
│   └── AppointmentService.java
├── repository/
│   ├── PatientRepository.java
│   ├── DoctorRepository.java
│   └── AppointmentRepository.java
├── model/
│   ├── Patient.java
│   ├── Doctor.java
│   └── Appointment.java
├── exception/
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
└── config/
```

## Setup Instructions

### Prerequisites
- Java 17+
- MySQL 8.x
- Maven 3.8+
- Eclipse / Spring Tool Suite (STS)

### Steps

1. **Create MySQL Database**
   ```sql
   CREATE DATABASE healthcare_db;
   ```

2. **Run the SQL schema** (optional — JPA auto-creates tables)
   ```
   mysql -u root -p healthcare_db < sql/schema.sql
   ```

3. **Configure database credentials** in `src/main/resources/application.properties`:
   ```
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Import into Eclipse/STS**
   - File → Import → Existing Maven Projects
   - Select the project folder
   - Click Finish

5. **Run the application**
   - Right-click `HealthcareApplication.java` → Run As → Spring Boot App

6. **Open in browser**
   ```
   http://localhost:8080
   ```

## Default Login
Use any credentials on the login page (demo mode).

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /dashboard | Admin dashboard |
| GET | /patients | List all patients |
| GET | /patients/add | Add patient form |
| POST | /patients/save | Save patient |
| GET | /patients/edit/{id} | Edit patient form |
| POST | /patients/update/{id} | Update patient |
| GET | /patients/delete/{id} | Delete patient |
| GET | /patients/cancel/{id} | Cancel registration |
| GET | /patients/search?keyword= | Search patients |
| GET | /doctors | List all doctors |
| GET | /doctors/add | Add doctor form |
| GET | /appointments | List appointments |
| GET | /appointments/book | Book appointment |
| GET | /appointments/history/{patientId} | Patient history |
