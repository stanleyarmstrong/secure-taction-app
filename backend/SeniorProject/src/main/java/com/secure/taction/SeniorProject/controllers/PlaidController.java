package com.secure.taction.SeniorProject.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import retrofit2.Response;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.AccountsGetRequest;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.request.LinkTokenCreateRequest;
import com.plaid.client.response.AccountsGetResponse;
import com.plaid.client.response.ItemPublicTokenExchangeResponse;
import com.plaid.client.response.LinkTokenCreateResponse;
import com.secure.taction.SeniorProject.dtos.PlaidPublicTokenDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.services.AccountService;
import com.secure.taction.SeniorProject.services.UserService;
import com.secure.taction.SeniorProject.utils.PlaidClientUtil;

@RestController
@RequestMapping("/plaid")
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
                  method=RequestMethod.POST)
  public ResponseEntity<Object> getAccessToken(@RequestBody PlaidPublicTokenDto publicToken) throws Exception {
    Response<ItemPublicTokenExchangeResponse> itemResponse = plaidClient.service()
            .itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(publicToken.getPublicToken()))
            .execute();
    Optional<UserDto> userToUpdate = userService.findByIdAndName(publicToken.getUserId(), 
                                                                publicToken.getUserName());
    if (itemResponse.isSuccessful()) {
      String accountId = itemResponse.body().getAccessToken();
      if (userToUpdate.isPresent()) {
        AccountDto newAccount = accountService.save(new AccountDto()
                                                    .withAccountId(accountId)
                                                    .withUserId(publicToken.getUserId()));
        userService.update(userToUpdate.get().addAccount(accountId));
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(itemResponse.errorBody().string(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value="/accounts", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateAccountValues(@RequestBody AccountDto account) throws Exception {
    if (account.getAccountId() == null) {
      return new ResponseEntity<>("\"message:\" Not authorized with access token",HttpStatus.NOT_ACCEPTABLE);
    }

    Response<AccountsGetResponse> response = plaidClient.service()
        .accountsGet(new AccountsGetRequest(account.getAccountId()))
        .execute();
    
    Optional<AccountDto> accountToUpdate = accountService
          .findByIdAndName(account.getAccountId(), account.getUserId());
    if (response.isSuccessful()) {
      if (accountToUpdate.isPresent()) {
        AccountDto updateAccount = accountToUpdate.get()
                                      .withAccountType(response.body()
                                                      .getAccounts().get(0).getType())
                                      .withBalance(BigDecimal.valueOf(response.body()
                                                  .getAccounts().get(0).getBalances().getCurrent()))
                                      .withAccountName(response.body()
                                                      .getAccounts().get(0).getName());
        updateAccount = accountService.update(updateAccount);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>("\"message\": unable to pull accounts from Plaid API", HttpStatus.BAD_REQUEST);
    }

  }
  
  @RequestMapping(value = "/transactions", method = RequestMethod.POST)
  public ResponseEntity<Object> getTransactions(@RequestBody PlaidPublicTokenDto publicToken) throws Exception {
    if (publicToken.getPublicToken() == null) {
      return new ResponseEntity<>("Not Authorized Action", HttpStatus.UNAUTHORIZED);
    }
  }

}
