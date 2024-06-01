package edu.yacoubi.springsecuritydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    // normally we don't to do so if a repository
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(
            Authentication auth) throws AuthenticationException {
        // In BasicController.login() method, we call authenticationManager.authenticate(token)
        // Then, Authentication Manager calls AuthenticationProvider's authenticate method.
        // Since JwtAuthenticationProvider is our custom authentication provider,
        // this method will be executed.
        String username = String.valueOf(auth.getPrincipal());
        String rawPassword = String.valueOf(auth.getCredentials());

        // user from db
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            // check password
            if (passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
                // if it matches, then we can initialize UsernamePasswordAuthenticationToken.
                // Attention! We used its 3 parameters constructor.
                return new UsernamePasswordAuthenticationToken(
                        username, rawPassword, new ArrayList<>()
                );

            }
        }
        throw new BadCredentialsException("Invalid credentials");
    }
    // Authentication Manager checks if the token is supported by this filter
    // to avoid unnecessary checks.
    @Override
    public boolean supports(Class<?> authClass) {
        return UsernamePasswordAuthenticationToken.class.equals(authClass);
    }
}
