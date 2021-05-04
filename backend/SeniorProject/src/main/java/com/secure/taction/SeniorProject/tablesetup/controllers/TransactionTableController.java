package com.secure.taction.SeniorProject.tablesetup.controllers;

import com.secure.taction.SeniorProject.tablesetup.services.TransactionTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table/transaction")
public class TransactionTableController {
    
    private final TransactionTableService tableService;

    @Autowired
    public TransactionTableController(TransactionTableService tableService) {
        this.tableService = tableService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createTransactionsTable() {
        return tableService.createTable();
    }

    @RequestMapping(value = "/killer/delete", method = RequestMethod.DELETE)
    public String deleteTransactionsTable() {
        return tableService.deleteTable();
    }
}
