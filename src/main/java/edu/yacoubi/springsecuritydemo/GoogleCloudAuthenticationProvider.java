package edu.yacoubi.springsecuritydemo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleCloudAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(
            Authentication auth) throws AuthenticationException {
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        // Mock Authentication
        User userFromGoogleCloud = getUserFromGoogleCloud(username, password);
        if (userFromGoogleCloud != null) {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    new ArrayList<>()
            );
            return token;
        }

        throw new BadCredentialsException("Invalid credentials");
    }

    private User getUserFromGoogleCloud(String username, String password) {
        // user from db
        Map<String, String> map = new HashMap<>();
        map.put("martin", "123");
        if (map.containsKey(username) && map.get(username).equals(password)) {
            return new User(
                    username,
                    password,
                    new ArrayList<>()
            );
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return UsernamePasswordAuthenticationToken.class.equals(authClass);
    }
}
