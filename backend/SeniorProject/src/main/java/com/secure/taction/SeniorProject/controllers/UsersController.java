package com.secure.taction.SeniorProject.controllers;

import com.secure.taction.SeniorProject.dtos.User;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {
    
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UsersController(UserRepository userRepository,
                           UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String addUserDetails(@RequestBody User user) {
		return userRepository.addUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserDetails(@PathVariable("id") String key) {
		return userRepository.getUser(key);
	}
}
