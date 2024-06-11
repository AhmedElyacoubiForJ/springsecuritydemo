package edu.yacoubi.springsecuritydemo.web;

import edu.yacoubi.springsecuritydemo.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientRepository patientRepository;

    @GetMapping("/patients")
    public String index() {
        return "patients";
    }
}
