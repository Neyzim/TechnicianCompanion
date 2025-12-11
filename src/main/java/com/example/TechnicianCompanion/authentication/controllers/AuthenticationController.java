package com.example.TechnicianCompanion.authentication.controllers;

import com.example.TechnicianCompanion.authentication.models.RefreshToken;
import com.example.TechnicianCompanion.authentication.repositories.RefreshTokenRepository;
import com.example.TechnicianCompanion.authentication.repositories.UserRepository;
import com.example.TechnicianCompanion.authentication.security.TokenService;
import com.example.TechnicianCompanion.authentication.dto.AuthenticationDTO;
import com.example.TechnicianCompanion.authentication.dto.LoginResponseDTO;
import com.example.TechnicianCompanion.authentication.dto.RegisterDTO;
import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.authentication.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var accessToken = tokenService.generateToken(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId().toString());

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO registerDTO){
        if(this.userRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> body){
        String requestRefreshToken = body.get("refresh_token");
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh Token Invalido"));
        refreshTokenService.verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        String newAccessToken = tokenService.generateToken(user);

       return ResponseEntity.ok(new LoginResponseDTO(newAccessToken, refreshToken.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody RefreshToken refreshToken) {
        Optional<RefreshToken> token = refreshTokenRepository.findByToken(refreshToken.getToken());
        if(!token.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refresh Token não encontrado");
        }

        RefreshToken existingToken = token.get();

        if (existingToken.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenService.deleteExpiredRefreshToken(existingToken);
            return ResponseEntity.ok("LOGOUT FEITO!");
        }
        return ResponseEntity.ok().body(existingToken);
    }
}
