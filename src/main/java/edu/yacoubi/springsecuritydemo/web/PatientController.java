package edu.yacoubi.springsecuritydemo.web;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import edu.yacoubi.springsecuritydemo.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientRepository patientRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int currentPage,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        //Page<Patient> patientsPage = patientRepository.findAll(PageRequest.of(currentPage, size));
        Page<Patient> patientsPage = patientRepository
                .findByNameContains(keyword, PageRequest.of(currentPage, size));
        model.addAttribute("patientsPage", patientsPage.getContent());
        //
        model.addAttribute("totalPages", new int[patientsPage.getTotalPages()]);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("keyword", keyword);
        //
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "keyword", defaultValue = "") String keyword ,
            @RequestParam(name = "page", defaultValue = "0") int currentPage) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + currentPage + "&keyword=" + keyword;
    }

    @GetMapping("/patient-form")
    public String patientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //bindingResult.getAllErrors().stream().forEach(System.out::println);
            return "patient-form";
        }
        patientRepository.save(patient);
        return "redirect:/index?keyword=" + patient.getName();
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, @RequestParam(name = "id") Long id) {
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);

        return "edit-patient-form";
    }
}
