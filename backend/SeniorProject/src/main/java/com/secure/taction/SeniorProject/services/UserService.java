package com.secure.taction.SeniorProject.services;

import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    public UserDto save(UserDto user) {

    }
    */
}
