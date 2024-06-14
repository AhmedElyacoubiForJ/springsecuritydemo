package edu.yacoubi.springsecuritydemo;

import edu.yacoubi.springsecuritydemo.entites.Patient;
import edu.yacoubi.springsecuritydemo.repository.PatientRepository;
import edu.yacoubi.springsecuritydemo.security.entities.AppRole;
import edu.yacoubi.springsecuritydemo.security.entities.AppUser;
import edu.yacoubi.springsecuritydemo.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Bean
    public CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save( new Patient(null, "Steve McQueen", LocalDate.of(1930, Month.MARCH, 24), false, 26) );
            patientRepository.save( new Patient(null, "Jesse James", LocalDate.of(1847, Month.SEPTEMBER, 5), false, 50) );
            patientRepository.save( new Patient(null, "John Wayne", LocalDate.of(1907, Month.JUNE, 26), true, 54) );
            patientRepository.save( new Patient(null, "Robert Mitchum", LocalDate.of(1917, Month.AUGUST, 17), false, 101) );
            patientRepository.save( new Patient(null, "Paul Newman", LocalDate.of(1917, Month.JANUARY, 26), false, 101) );
            patientRepository.save( new Patient(null, "Audie Murphy", LocalDate.of(1925, Month.JUNE, 20), false, 343) );
        };
    }

    @Bean
    CommandLineRunner createUsers(
            JdbcUserDetailsManager jdbcUserDetailsManager,
            PasswordEncoder encoder) {
        return args -> {
            // per default user is enabled
            try {
                UserDetails user1 = jdbcUserDetailsManager.loadUserByUsername("user1");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user1").password(encoder.encode("123")).roles("USER").build()
                );
            }
            try {
                UserDetails user2 = jdbcUserDetailsManager.loadUserByUsername("user2");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user2").password(encoder.encode("123")).roles("USER").build()
                );
            }
            try {
                UserDetails admin = jdbcUserDetailsManager.loadUserByUsername("admin");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin").password(encoder.encode("123")).roles("USER", "ADMIN").build()
                );
            }
        };
    }

    @Bean
    CommandLineRunner accountServiceCreateUsers(AccountService accountService) {
        return args -> {
//            accountService.addNewRole("USER");
//            accountService.addNewRole("ADMIN");


            AppUser appUser;

            appUser = accountService.loadUserByUsername("user11");
            if (appUser == null) {
                accountService.addNewUser("user11", "123", "user11@gmail.com", "123");
                accountService.addRoleToUser("user11", "USER");
            }

            appUser = accountService.loadUserByUsername("user22");
            if (appUser == null) {
                accountService.addNewUser("user22", "123", "user22@gmail.com", "123");
                accountService.addRoleToUser("user22", "USER");
            }

            appUser = accountService.loadUserByUsername("admin33");
            if (appUser == null) {
                accountService.addNewUser("admin33", "123", "user33@gmail.com", "123");
                accountService.addRoleToUser("admin33", "USER");
                accountService.addRoleToUser("admin33", "ADMIN");
            }
        };
    }
}
