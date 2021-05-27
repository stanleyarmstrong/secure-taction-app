package com.secure.taction.SeniorProject.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import com.secure.taction.SeniorProject.dtos.transaction.TransactionDto;
import com.secure.taction.SeniorProject.services.TransactionService;
import com.secure.taction.SeniorProject.utils.SnsClientUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/{id}/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDto> findById(
                                        @PathVariable("id") String id,
                                        @PathVariable("accountId") String accountId) {
        return transactionService.findByIdAndAccountId(id, accountId)
                    .map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Debugging endpoint
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@Valid @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(
            transactionService.save(transactionDto),
            HttpStatus.CREATED
        );
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<Object> testSnsCreate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();  
        TransactionDto transactionDto = new TransactionDto()
            .withTransactionId(UUID.randomUUID().toString().toUpperCase())
            .withAccountId(SnsClientUtil.TEST_ACCOUNT_ID)
            .withAmount(BigDecimal.valueOf(150.44))
            .withVendor("Apple Store")
            .withDate(dtf.format(now))
            .withType("person")
            .withCategories(Collections.singletonList("Luxury"));
        return new ResponseEntity<>(
            transactionService.save(transactionDto),
            HttpStatus.CREATED
        );
    }

    // Debugging endpoint
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.findByIdAndAccountId("bogus", 
                                                       "bogus").isPresent()
                    ? new ResponseEntity<>(transactionService.update(transactionDto), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id,
                                         @PathVariable("accountId") String accountId) {
        ResponseEntity<Object> toReturn = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (transactionService.findByIdAndAccountId(id, accountId).isPresent()) {
            transactionService.deleteByIdAndUserId(id, accountId);
            toReturn = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return toReturn;
    }
}
