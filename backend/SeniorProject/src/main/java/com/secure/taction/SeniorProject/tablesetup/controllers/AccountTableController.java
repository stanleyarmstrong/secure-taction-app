package com.secure.taction.SeniorProject.tablesetup.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.secure.taction.SeniorProject.tablesetup.services.AccountTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table/account")
public class AccountTableController {
    
    private final AccountTableService tableService;
    @Autowired
    public AccountTableController(AccountTableService tableService) {
        this.tableService = tableService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createUsersTable() {
        return tableService.createTable();
    }

    @RequestMapping(value = "/killer/delete", method = RequestMethod.DELETE)
    public String deleteUsersTable() {
        // This method is for testing purposes only DON'T CALL IT
        return tableService.deleteTable();
    }
}
