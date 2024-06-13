package edu.yacoubi.springsecuritydemo.security.repo;

import edu.yacoubi.springsecuritydemo.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
