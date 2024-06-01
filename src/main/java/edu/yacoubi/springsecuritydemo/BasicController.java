package edu.yacoubi.springsecuritydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/* rest controller */
@RestController
@RequiredArgsConstructor
class BasicController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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
        String jwtToken = jwtUtil.generate(loginDTO.getUsername());
        return ResponseEntity.ok(jwtToken);
    }
}
