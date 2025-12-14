package com.example.TechnicianCompanion.authentication.service;

import com.example.TechnicianCompanion.authentication.models.User;
import com.example.TechnicianCompanion.authentication.repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<String> getUserByName(String id){
        Optional<User> findedUser = userRepository.findById(id);
        return findedUser.map(User::getName);
    }
}
