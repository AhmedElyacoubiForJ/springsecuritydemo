package edu.yacoubi.springsecuritydemo.security.service;

import edu.yacoubi.springsecuritydemo.security.entities.AppRole;
import edu.yacoubi.springsecuritydemo.security.entities.AppUser;
import edu.yacoubi.springsecuritydemo.security.entities.AppUserDetails;
import edu.yacoubi.springsecuritydemo.security.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    //private final AccountService accountService;
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        //AppUser appUser = accountService.loadUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(String.format("User username %s found", username));
        }
        // first approach
//        String[] roles = appUser.getRoles().stream().map(r -> r.getRole()).toArray(String[]::new);
//        UserDetails userDetails = User
//                .withUsername(appUser.getUsername())
//                .password(appUser.getPassword())
//                .roles(roles)
//                .build();

        // second approach
        List<SimpleGrantedAuthority> authorities = appUser
                .getRoles()
                .stream()
                .map(appRole -> new SimpleGrantedAuthority(appRole.getRole()))
                .collect(Collectors.toList());

        UserDetails userDetails = User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(authorities)
                .build();

        // third approach
//        AppUserDetails userDetails = new AppUserDetails(
//                appUser.getUsername(),
//                appUser.getPassword(),
//                appUser.getRoles().stream()
//                       .map(appRole -> new SimpleGrantedAuthority(appRole.getRole()))
//                       .collect(Collectors.toList())
//        );

        return userDetails;
    }
}
