package edu.yacoubi.springsecuritydemo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/* rest controller */
@RestController
@RequiredArgsConstructor
class BasicController {
    private final AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        // the constructor set the authentication to false per default.
        // we use the static method because is more readable
        // and so the authentication manager take charge of the authentication
        UsernamePasswordAuthenticationToken unAuthenticated = UsernamePasswordAuthenticationToken
                .unauthenticated(loginDTO.getUsername(), loginDTO.getPassword());

        authenticationManager.authenticate(unAuthenticated);
        return ResponseEntity.ok("Login");
    }
}

/* request object class */
@Data
@NoArgsConstructor
class LoginDTO {
    private String username;
    private String password;
}

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}