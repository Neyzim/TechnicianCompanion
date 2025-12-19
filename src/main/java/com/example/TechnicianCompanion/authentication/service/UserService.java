package com.example.TechnicianCompanion.authentication.service;

import com.example.TechnicianCompanion.authentication.dto.AuthenticationDTO;
import com.example.TechnicianCompanion.authentication.dto.LoginResponseDTO;
import com.example.TechnicianCompanion.authentication.dto.RegisterDTO;
import com.example.TechnicianCompanion.authentication.mappers.UserMapper;
import com.example.TechnicianCompanion.authentication.models.RefreshToken;
import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.authentication.repositories.RefreshTokenRepository;
import com.example.TechnicianCompanion.authentication.repositories.UserRepository;
import com.example.TechnicianCompanion.authentication.security.TokenService;
import com.example.TechnicianCompanion.authentication.security.UserConfigurationImp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    public UserService(UserRepository userRepository, UserMapper mapper, AuthenticationManager authenticationManager, TokenService tokenService, RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;

        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<String> getUserByName(String id){
        Optional<User> findedUser = userRepository.findById(id);
        return findedUser.map(User::getName);
    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public RegisterDTO createNewUserWithPasswordEncoded(RegisterDTO registerDTO){
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var user = new User(registerDTO.login(), encryptedPassword, registerDTO.role());
        user.setName(registerDTO.name());
        userRepository.save(user);
        return mapper.map(user);
    }

    public LoginResponseDTO loginUsingUser(AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (UserConfigurationImp) auth.getPrincipal();
        var accessToken = tokenService.generateToken(user.getUser());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUser().getId());
        return new LoginResponseDTO(accessToken, refreshToken.getToken());
    }

    public String logout(String refreshToken) {
        Optional<RefreshToken> token = refreshTokenRepository.findByToken(refreshToken);
        if (token.isEmpty()) {
            return "Refresh Token não encotrado";
        }
        RefreshToken existingToken = token.get();
        refreshTokenService.deleteToken(existingToken);
        return "Logout feito com Sucesso!";
    }


    public LoginResponseDTO refreshAndCreateNewToken(Map<String, String> body){
        String requestRefreshToken = body.get("refresh_token");
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh Token Invalido"));
        refreshTokenService.verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        String newAccessToken = tokenService.generateToken(user);

        return new LoginResponseDTO(newAccessToken, refreshToken.getToken());

    }

}
