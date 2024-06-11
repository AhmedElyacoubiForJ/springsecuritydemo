package edu.yacoubi.springsecuritydemo.repository;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContains(String keyword, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.name LIKE :x")
    Page<Patient> search(@Param("x") String keyword, Pageable pageable);
}
