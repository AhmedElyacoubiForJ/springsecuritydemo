package edu.yacoubi.springsecuritydemo.security.service;

import edu.yacoubi.springsecuritydemo.security.entities.AppRole;
import edu.yacoubi.springsecuritydemo.security.entities.AppUser;
import edu.yacoubi.springsecuritydemo.security.repo.AppRoleRepository;
import edu.yacoubi.springsecuritydemo.security.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password,
                              String email, String confirmPassword) {

        AppUser appUser = appUserRepository.findByUsername(username);
        //
        if (appUser != null)
            throw new RuntimeException("User already exists");
        if (!password.equals(confirmPassword))
            throw new RuntimeException("Password not match");
        //
        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(String role) {
        Optional<AppRole> appRole = appRoleRepository.findById(role);
        if (appRole.isPresent())
            throw new RuntimeException("Role already exists");

        return appRoleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User not found");

        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(
                        () -> new RuntimeException("Role not found")
                );

        appUser.getRoles().add(appRole);
        // don't to save explicitly because the method is transactional
        // user will be automatically saved
        //userRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User not found");

        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(
                        () -> new RuntimeException("Role not found")
                );

        appUser.getRoles().remove(appRole);
        //userRepository.save(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
