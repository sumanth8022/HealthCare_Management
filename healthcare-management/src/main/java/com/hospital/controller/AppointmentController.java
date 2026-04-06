package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.service.PatientService;
import com.hospital.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments/list";
    }

    @GetMapping("/book")
    public String showBookForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getActivePatients());
        model.addAttribute("doctors", doctorService.getAvailableDoctors());
        return "appointments/book";
    }

    @PostMapping("/save")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                  BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("patients", patientService.getActivePatients());
            model.addAttribute("doctors", doctorService.getAvailableDoctors());
            return "appointments/book";
        }
        appointmentService.saveAppointment(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment booked successfully!");
        return "redirect:/appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        appointmentService.cancelAppointment(id);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment cancelled!");
        return "redirect:/appointments";
    }

    @GetMapping("/complete/{id}")
    public String completeAppointment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        appointmentService.completeAppointment(id);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment marked as completed!");
        return "redirect:/appointments";
    }

    @GetMapping("/history/{patientId}")
    public String appointmentHistory(@PathVariable Long patientId, Model model) {
        model.addAttribute("appointments", appointmentService.getAppointmentsByPatient(patientId));
        model.addAttribute("patient", patientService.getPatientById(patientId));
        return "appointments/history";
    }
}
