package com.secure.taction.SeniorProject.controllers;

import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.services.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    
    @RequestMapping(value = "/{id}/{userId}", method = RequestMapping.GET)
    public ResponseEntity<BudgetDto> findById(@PathVariable("id") String budgetId,
                                              @PathVariable("userId") String userId) {
        return budgetService.
    }
}
