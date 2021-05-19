package com.secure.taction.SeniorProject.controllers;

import java.util.List;

import com.secure.taction.SeniorProject.dtos.accounts.AccountAndBudgetDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.services.AccountService;

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
@RequestMapping("/account")
public class AccountController {
    
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void search() {
        // STUB
        // Return type and functionality TBD
    }

	@RequestMapping(value = "/{id}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<AccountDto> findById(@PathVariable("id") String id,
                                            @PathVariable("userId") String userId) {
		return accountService.findByIdAndName(id, userId)
                .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
    /*
    *
    *   THIS METHOD IS DEPRECATED. Still useful for simple debugging of
    *   Based AccountDto creation, but will not be used for actual
    *   Database transaction
    *
    */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody AccountDto account) {
		return new ResponseEntity<>(
            accountService.save(account),
            HttpStatus.CREATED); 
	}

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody AccountDto account) {
        return accountService.findByIdAndName(account.getAccountId(), account.getUserId()).isPresent()
                ? new ResponseEntity<>(accountService.update(account), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/{userid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id,
                                         @PathVariable("userid") String userId) {
        ResponseEntity<Object> toReturn = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (accountService.findByIdAndName(id,userId).isPresent()) {
            accountService.deleteByIdAndName(id, userId);
            toReturn = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return toReturn;
    }
}

