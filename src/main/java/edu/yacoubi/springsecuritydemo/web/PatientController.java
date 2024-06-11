package edu.yacoubi.springsecuritydemo.web;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import edu.yacoubi.springsecuritydemo.repository.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientRepository patientRepository;

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
}
