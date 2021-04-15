package com.secure.taction.SeniorProject.controllers;

import javax.validation.Valid;

import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.services.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    
    @RequestMapping(value = "/{id}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<BudgetDto> findById(@PathVariable("id") String budgetId,
                                              @PathVariable("userId") String userId) {
        return budgetService.findByIdAndUserId(budgetId, userId)
                    .map(budget -> new ResponseEntity<>(budget, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND))
        ;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@Valid @RequestBody BudgetDto budget) {
        return new ResponseEntity<>(
            budgetService.save(budget),
            HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@Valid @RequestBody BudgetDto budget) {
       return budgetService.findByIdAndUserId(budget.getBudgetId(), budget.getUserId()).isPresent()
                ? new ResponseEntity<>(budgetService.update(budget), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String budgetId,
                                         @PathVariable("userId") String userId) {
        ResponseEntity<Object> toReturn = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (budgetService.findByIdAndUserId(budgetId, userId).isPresent()) {
            budgetService.deleteByIdAndUserId(budgetId, userId);
            toReturn = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return toReturn;
    }

}
