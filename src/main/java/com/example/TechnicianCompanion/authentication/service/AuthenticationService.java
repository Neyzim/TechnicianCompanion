package com.example.TechnicianCompanion.authentication.service;

import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.authentication.repositories.UserRepository;
import com.example.TechnicianCompanion.authentication.security.UserConfigurationImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + login);
        }
        return new UserConfigurationImp(user);
    }
}