package edu.yacoubi.springsecuritydemo;

import edu.yacoubi.springsecuritydemo.security.entities.AppRole;
import edu.yacoubi.springsecuritydemo.security.entities.AppUser;
import edu.yacoubi.springsecuritydemo.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Optional;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner accountServiceCreateUsers(AccountService accountService) {
        return args -> {

            Optional<AppRole> userRoleOptional = accountService.getAppRoleById("USER");
            if (!userRoleOptional.isPresent()) {
                accountService.addNewRole("USER");
            }
            Optional<AppRole> adminRoleOptional = accountService.getAppRoleById("ADMIN");
            if (!adminRoleOptional.isPresent()) {
                accountService.addNewRole("ADMIN");
            }

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
