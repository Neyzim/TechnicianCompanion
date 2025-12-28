package com.example.TechnicianCompanion.authentication.controllers;

import com.example.TechnicianCompanion.authentication.dto.LogoutRequestDTO;
import com.example.TechnicianCompanion.authentication.dto.AuthenticationDTO;
import com.example.TechnicianCompanion.authentication.dto.LoginResponseDTO;
import com.example.TechnicianCompanion.authentication.dto.RegisterDTO;
import com.example.TechnicianCompanion.authentication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Usado para fazer login!")
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
    @Operation(summary = "Usado para registrar um novo usuario no Banco de Dados")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO registerDTO){
        if(userService.findUserByLogin(registerDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        RegisterDTO newUser = userService.createNewUserWithPasswordEncoded(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Atualiza o Access Token", description = "Atualiza o Acess Token usando um Refresh Token como chave para tal")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> body){
       LoginResponseDTO loginResponseDTO = userService.refreshAndCreateNewToken(body);
       return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }

    @PostMapping("/logout")
    @Operation(summary = "Desloga um usuário", description = "O processo de Logout recebe um refresh token e o apaga do Banco de Dados!")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutRequestDTO requestDTO) {
        String result = userService.logout(requestDTO.token());
        return ResponseEntity.ok(result);
    }
}
