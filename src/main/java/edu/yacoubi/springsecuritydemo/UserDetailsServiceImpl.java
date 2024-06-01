package edu.yacoubi.springsecuritydemo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {
        // user from db
        Map<String, String> map = new HashMap<>();
        map.put("leo", encoder.encode("123"));

        if (map.containsKey(username)) {
            return new User(
                    username,
                    map.get(username),
                    new ArrayList<GrantedAuthority>()
            );
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }
}
