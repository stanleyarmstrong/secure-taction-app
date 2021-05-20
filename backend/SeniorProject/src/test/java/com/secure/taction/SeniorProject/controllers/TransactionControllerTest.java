package com.secure.taction.SeniorProject.controllers;

import static org.mockito.ArgumentMatchers.anyString;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.secure.taction.SeniorProject.dtos.transaction.TransactionDto;
import com.secure.taction.SeniorProject.models.Transaction;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest extends BaseControllerTest {
    
    final private String transactionId = UUID.randomUUID().toString().toUpperCase();
    final private String accountId = UUID.randomUUID().toString().toUpperCase();
    final private BigDecimal amount = new BigDecimal("1234.56");
    final private String address = "1234 Location Pl.";
    final private String vendor = "Congazon";
    final private String date = "1/2/34";
    final private List<String> categories = Collections.singletonList("Singular Category");

    @Test
    @WithMockUser(roles ={})
    public void findById_found() throws Exception {
        TransactionDto stubTransaction = new TransactionDto();
        when(transactionService.findByIdAndAccountId(anyString(), anyString()))
            .thenReturn(Optional.of(stubTransaction));
        mvc.perform(get("/transaction/"+transactionId+"/"+accountId))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void findById_notFound() throws Exception {
        Optional<TransactionDto> stubTransaction = Optional.empty(); 
        when(transactionService.findByIdAndAccountId(anyString(), anyString()))
            .thenReturn(stubTransaction);
        mvc.perform(get("/transaction/"+transactionId+"/"+accountId))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {})
    public void create() throws Exception {
        final TransactionDto dto = new TransactionDto()
                    .withTransactionId(transactionId)
                    .withAccountId(accountId)
                    .withAmount(amount)
                    .withAddress(address)
                    .withVendor(vendor)
                    .withDate(date)
                    .withCategories(categories);
        when(transactionService.save(any(TransactionDto.class)))
            .thenReturn(dto);
        mvc.perform(post("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles ={})
    public void update_notFound() throws Exception {
        final TransactionDto dto = new TransactionDto()
                    .withTransactionId(transactionId)
                    .withAccountId(accountId)
                    .withAmount(amount)
                    .withAddress(address)
                    .withVendor(vendor)
                    .withDate(date)
                    .withCategories(categories);
        when(transactionService.findByIdAndAccountId(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(put("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles ={})
    public void update_found() throws Exception {
        final String NEW_ADDRESS = "new address for testing update";
        final TransactionDto dto = new TransactionDto()
                    .withTransactionId(transactionId)
                    .withAccountId(accountId)
                    .withAmount(amount)
                    .withAddress(address)
                    .withVendor(vendor)
                    .withDate(date)
                    .withCategories(categories);
        when(transactionService.findByIdAndAccountId(anyString(), anyString()))
            .thenReturn(Optional.of(dto));
        when(transactionService.update(any(TransactionDto.class)))
            .thenReturn(dto.withAddress(NEW_ADDRESS));
        mvc.perform(put("/transaction")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void deleteTransaction_found() throws Exception {
        when(transactionService.findByIdAndAccountId(anyString(), anyString()))
            .thenReturn(Optional.of(new TransactionDto()));
        mvc.perform(delete("/transaction")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = {})
    public void deleteTransaction_notFound() throws Exception {
        when(transactionService.findByIdAndAccountId(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(delete("/transaction")).andExpect(status().isMethodNotAllowed());
    }
}
