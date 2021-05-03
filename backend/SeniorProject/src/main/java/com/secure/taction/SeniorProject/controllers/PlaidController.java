package com.secure.taction.SeniorProject.controllers;

import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
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
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.services.AccountService;
import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.utils.PlaidClientUtil;

@RestController("/plaid")
@RequestMapping
public class PlaidController {

  private final UserService userService;
  private final AccountService accountService;
  private PlaidClient plaidClient;
  @Autowired
  public PlaidController(UserService userService,
                         AccountService accountService) {
    this.userService = userService;
    this.accountService = accountService;
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
  public ResponseEntity<Object> getAccessToken(@RequestParam("public_token") String publicToken,
                                                             @RequestParam("userId") String userId,
                                                             @RequestParam("userName") String userName) throws Exception {
//    Response<ItemPublicTokenExchangeResponse> itemResponse = plaidClient.service()
//            .itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicToken))
//            .execute();
    Optional<UserDto> userToUpdate = userService.findByIdAndName(userId, userName);
//    if (itemResponse.isSuccessful()) {
    if (true && userToUpdate.isPresent()) {
//      String accountId = itemResponse.body().getAccessToken();
      String accountId = UUID.randomUUID().toString().toUpperCase();
      AccountDto newAccount = accountService.save(new AccountDto()
                                                  .withAccountId(accountId)
                                                  .withUserId(userToUpdate.get().getUserId()));
      userService.update(userToUpdate.get().addAccount(accountId)) ;
      return new ResponseEntity<>(newAccount, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      //return new ResponseEntity<>(itemResponse.errorBody().string(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
}
