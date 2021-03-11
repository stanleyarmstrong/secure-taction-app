package com.secure.taction.SeniorProject.controllers;

import com.secure.taction.SeniorProject.dtos.User;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UsersController(UserRepository userRepository,
                           UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
	
	@PostMapping("/user")
	public String addUserDetails(@RequestBody User user) {
		return userRepository.addUser(user);
	}

	@GetMapping("/user/{key}")
	public User getUserDetails(@PathVariable("key") String key) {
		return userRepository.getUser(key);
	}
}
