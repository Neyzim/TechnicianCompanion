package com.example.TechnicianCompanion.authentication.dto;

import com.example.TechnicianCompanion.authentication.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
