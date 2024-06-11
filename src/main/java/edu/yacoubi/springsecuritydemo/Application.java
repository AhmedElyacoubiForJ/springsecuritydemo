package edu.yacoubi.springsecuritydemo;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import edu.yacoubi.springsecuritydemo.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            patientRepository.save( new Patient(null, "John", new Date(), false, 26) );
            patientRepository.save( new Patient(null, "Jane", new Date(), false, 3) );
            patientRepository.save( new Patient(null, "Jack", new Date(), true, 54) );
            patientRepository.save( new Patient(null, "Jill", new Date(), false, 101) );

            patientRepository.findAll().forEach(System.out::println);
        };
    }

}
