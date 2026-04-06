package com.hospital.controller;

import com.hospital.service.PatientService;
import com.hospital.service.DoctorService;
import com.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalPatients", patientService.getTotalCount());
        model.addAttribute("activePatients", patientService.getActiveCount());
        model.addAttribute("totalDoctors", doctorService.getTotalCount());
        model.addAttribute("totalAppointments", appointmentService.getTotalCount());
        model.addAttribute("scheduledAppointments", appointmentService.getScheduledCount());
        model.addAttribute("todayAppointments", appointmentService.getTodayAppointments());
        return "dashboard";
    }
}
