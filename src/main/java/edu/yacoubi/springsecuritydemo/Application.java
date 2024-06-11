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
            patientRepository.save( new Patient(null, "John", LocalDate.of(1970, Month.NOVEMBER, 12), false, 26) );
            patientRepository.save( new Patient(null, "Jane", LocalDate.of(2010, Month.JANUARY, 28), false, 3) );
            patientRepository.save( new Patient(null, "Jack", LocalDate.of(1999, Month.MAY, 9), true, 54) );
            patientRepository.save( new Patient(null, "Jill", LocalDate.of(2001, Month.JULY, 10), false, 101) );

            patientRepository.findAll().forEach(System.out::println);
        };
    }

}
