package com.example.TechnicianCompanion.repositories;

import com.example.TechnicianCompanion.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
