package com.example.TechnicianCompanion.authentication.repositories;

import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.authentication.security.UserConfigurationImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);

}
