package com.example.TechnicianCompanion.authentication.service;

import com.example.TechnicianCompanion.authentication.models.RefreshToken;
import com.example.TechnicianCompanion.authentication.repositories.RefreshTokenRepository;
import com.example.TechnicianCompanion.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${security.jwt.refresh-expiration}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).orElseThrow());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(Instant.now().plusMillis(refreshTokenDurationMs));

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiresAt().isBefore(Instant.now())) {
           return null;
        }
        return token;
    }

    public void deleteExpiredRefreshToken(RefreshToken refreshToken) {
        RefreshToken token = verifyExpiration(refreshToken);
        if (token == null) {
            refreshTokenRepository.delete(refreshToken);
        }
    }
}
