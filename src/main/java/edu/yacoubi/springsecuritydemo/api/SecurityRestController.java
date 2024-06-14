package edu.yacoubi.springsecuritydemo.api;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityRestController {

   /*
    // injection
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }*/

    @GetMapping("/profile")
    public Authentication authentication() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
}
