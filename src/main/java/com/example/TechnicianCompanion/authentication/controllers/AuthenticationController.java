package com.example.TechnicianCompanion.authentication.controllers;

import com.example.TechnicianCompanion.authentication.dto.LogoutRequestDTO;
import com.example.TechnicianCompanion.authentication.dto.AuthenticationDTO;
import com.example.TechnicianCompanion.authentication.dto.LoginResponseDTO;
import com.example.TechnicianCompanion.authentication.dto.RegisterDTO;
import com.example.TechnicianCompanion.authentication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        try {
            LoginResponseDTO response = userService.loginUsingUser(authenticationDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO registerDTO){
        if(userService.findUserByLogin(registerDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        RegisterDTO newUser = userService.createNewUserWithPasswordEncoded(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> body){
       LoginResponseDTO loginResponseDTO = userService.refreshAndCreateNewToken(body);
       return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutRequestDTO requestDTO) {
        String result = userService.logout(requestDTO.token());
        return ResponseEntity.ok(result);
    }
}
