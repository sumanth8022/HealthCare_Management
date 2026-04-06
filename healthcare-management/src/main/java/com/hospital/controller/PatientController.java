package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/add";
    }

    @PostMapping("/save")
    public String savePatient(@Valid @ModelAttribute("patient") Patient patient,
                              BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patients/add";
        }
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Patient registered successfully!");
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patients/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @Valid @ModelAttribute("patient") Patient patient,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patients/edit";
        }
        patientService.updatePatient(id, patient);
        redirectAttributes.addFlashAttribute("successMessage", "Patient updated successfully!");
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        patientService.deletePatient(id);
        redirectAttributes.addFlashAttribute("successMessage", "Patient deleted successfully!");
        return "redirect:/patients";
    }

    @GetMapping("/cancel/{id}")
    public String cancelRegistration(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        patientService.cancelRegistration(id);
        redirectAttributes.addFlashAttribute("successMessage", "Patient registration cancelled!");
        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("patients", patientService.searchPatients(keyword));
        model.addAttribute("keyword", keyword);
        return "patients/list";
    }
}
