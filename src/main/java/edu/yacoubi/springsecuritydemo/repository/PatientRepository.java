package edu.yacoubi.springsecuritydemo.repository;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
