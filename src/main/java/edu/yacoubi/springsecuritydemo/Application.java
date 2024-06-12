package edu.yacoubi.springsecuritydemo;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import edu.yacoubi.springsecuritydemo.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class Application {
    private final PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
//            patientRepository.save( new Patient(null, "Steve McQueen", LocalDate.of(1930, Month.MARCH, 24), false, 26) );
//            patientRepository.save( new Patient(null, "Jesse James", LocalDate.of(1847, Month.SEPTEMBER, 5), false, 50) );
//            patientRepository.save( new Patient(null, "John Wayne", LocalDate.of(1907, Month.JUNE, 26), true, 54) );
//            patientRepository.save( new Patient(null, "Robert Mitchum", LocalDate.of(1917, Month.AUGUST, 17), false, 101) );
//            patientRepository.save( new Patient(null, "Paul Newman", LocalDate.of(1917, Month.JANUARY, 26), false, 101) );
//            patientRepository.save( new Patient(null, "Audie Murphy", LocalDate.of(1925, Month.JUNE, 20), false, 343) );

//            patientRepository.findAll().forEach(System.out::println);
        };
    }
}
