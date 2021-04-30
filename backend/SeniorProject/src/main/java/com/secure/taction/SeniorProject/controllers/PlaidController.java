package com.secure.taction.SeniorProject.controllers;

import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import retrofit2.Response;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.request.LinkTokenCreateRequest;
import com.plaid.client.response.ItemPublicTokenExchangeResponse;
import com.plaid.client.response.LinkTokenCreateResponse;
import com.plaid.client.response.LinkTokenGetResponse;
import com.secure.taction.SeniorProject.dtos.user.UserDto;

import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.utils.PlaidClientUtil;

@RestController("/plaid")
@RequestMapping
public class PlaidController {

  private final UserService userService;
  private PlaidClient plaidClient;
  @Autowired
  public PlaidController(UserService userService) {
    this.userService = userService;
    plaidClient = PlaidClientUtil.getPlaidClient();
  }

  /*
  *
  * This endpoint will create a linked token and return it in JSON as 
  * an http response, this value will be used by the front end to intialize
  * Plaid's LINK Ui
  *
  */
  @RequestMapping(value="/create_link_token/{id}/{userId}", method=RequestMethod.GET)
  public ResponseEntity<Object> create(@PathVariable("id") String id, 
                                       @PathVariable("userId") String userId) throws Exception {
    
    // makes sure that the user is in before making an accout
    UserDto userDto = this.userService.findByIdAndName(id, userId).orElseGet(null);
    if (userDto == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    LinkTokenCreateRequest.User user = new LinkTokenCreateRequest.User(id);

    Response<LinkTokenCreateResponse> response = plaidClient.service()
        .linkTokenCreate(
          new LinkTokenCreateRequest(
            user, 
            "Secure Taction", 
            Arrays.asList("transactions"), 
            Collections.singletonList("US"), 
            "en").withWebhook("https://sample.webhook.com")
          ).execute()
    ;
    return new ResponseEntity<>(response.body(), HttpStatus.OK);
  }

  @RequestMapping(value="/get_access_token", 
                  method=RequestMethod.POST, 
                  consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
  public @ResponseBody ResponseEntity<Object> getAccessToken(@RequestParam("public_token") String publicToken) throws Exception {
    Response<ItemPublicTokenExchangeResponse> itemResponse = plaidClient.service()
            .itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicToken))
            .execute();
    
    if (itemResponse.isSuccessful()) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(itemResponse.errorBody().string(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
}
