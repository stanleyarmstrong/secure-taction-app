package com.secure.taction.SeniorProject.controllers;

import java.net.*;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.plaid.client.request.LinkTokenCreateRequest;
import com.plaid.client.response.LinkTokenGetResponse;
import com.secure.taction.SeniorProject.dtos.user.UserDto;

import com.secure.taction.SeniorProject.services.UserService;

@RestController("/plaid")
@RequestMapping
public class PlaidController {
  private final UserService userService;

  @Autowired
  public PlaidController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value="/create_link_token/{id}/{userId}", method=RequestMethod.GET)
  public ResponseEntity<Object> create(@PathVariable("id") String id, 
                                       @PathVariable("userId") String userId) {
    
    // makes sure that the user is in before making an accout
    UserDto userDto = this.userService.findByIdAndName(id, userId).orElseGet(null);
    if (userDto == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    LinkTokenCreateRequest.User user = new LinkTokenCreateRequest.User(id);

   


    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  
}
