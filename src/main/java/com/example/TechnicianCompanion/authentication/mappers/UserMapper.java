package com.example.TechnicianCompanion.authentication.mappers;

import com.example.TechnicianCompanion.authentication.dto.RegisterDTO;
import com.example.TechnicianCompanion.authentication.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(RegisterDTO registerDTO){
        User user = new User();
        user.setName(registerDTO.name());
        user.setLogin(registerDTO.login());
        user.setPassword(registerDTO.password());
        return user;
    }

    public RegisterDTO map(User user){
        return new RegisterDTO(
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                user.getName()
        );
    }
}
