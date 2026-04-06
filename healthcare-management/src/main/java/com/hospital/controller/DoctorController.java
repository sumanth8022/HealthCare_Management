package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/add";
    }

    @PostMapping("/save")
    public String saveDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctors/add";
        }
        doctorService.saveDoctor(doctor);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor added successfully!");
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "doctors/edit";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id, @Valid @ModelAttribute("doctor") Doctor doctor,
                               BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctors/edit";
        }
        doctorService.updateDoctor(id, doctor);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor updated successfully!");
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        doctorService.deleteDoctor(id);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor deleted successfully!");
        return "redirect:/doctors";
    }
}
