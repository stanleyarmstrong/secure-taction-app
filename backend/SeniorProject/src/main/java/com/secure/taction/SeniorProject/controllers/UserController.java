package com.secure.taction.SeniorProject.controllers;

import java.util.List;

import javax.validation.Valid;

import com.secure.taction.SeniorProject.dtos.accounts.AccountAndBudgetDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void search() {
        // STUB
        // Return type and functionality TBD
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountAndBudgetDto> findAccountAndBudget(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findAccountWithBudgets(userId), HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> findById(@PathVariable("id") String id,
                                            @PathVariable("username") String username) {
		return userService.findByIdAndName(id, username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody UserDto user) {
		return new ResponseEntity<>(
            userService.save(user),
            HttpStatus.CREATED); 
	}

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@Valid @RequestBody UserDto user) {
        return userService.findByIdAndName(user.getUserId(), user.getUserName()).isPresent()
                ? new ResponseEntity<>(userService.update(user), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id,
                                         @PathVariable("username") String username) {
        ResponseEntity<Object> toReturn = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (userService.findByIdAndName(id,username).isPresent()) {
            userService.deleteByIdAndName(id, username);
            toReturn = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return toReturn;
    }
}
