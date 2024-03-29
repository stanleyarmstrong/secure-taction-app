package com.secure.taction.SeniorProject.tablesetup.controllers;

import com.secure.taction.SeniorProject.tablesetup.services.UserTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table/user")
public class UserTableController {
   
    private final UserTableService tableService;
    @Autowired
    public UserTableController(UserTableService tableService) {
        this.tableService = tableService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createUsersTable() {
        return tableService.createTable();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET) 
    public void listTables() {
        tableService.listTables();
    }

    @RequestMapping(value = "/killer/delete", method = RequestMethod.DELETE)
    public String deleteUsersTable() {
        // This method is for testing purposes only DON'T CALL IT
        return tableService.deleteTable();
    }
}
