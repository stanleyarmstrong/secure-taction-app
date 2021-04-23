package com.secure.taction.SeniorProject.controllers;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest extends BaseControllerTest {
    final private String accountId = UUID.randomUUID().toString().toUpperCase();
    final private String userId = UUID.randomUUID().toString().toUpperCase();
    final private String accountType = "checking";
    final private BigDecimal balance = new BigDecimal("1000.00"); 
    final private String accountName = accountId + "'s Checking Account";

    @Test
    @WithMockUser(roles = {})
    public void findById_found() throws Exception {
        AccountDto stubAccount = new AccountDto();
        when(accountService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.of(stubAccount));
        mvc.perform(get("/account/"+accountId+"/"+userId))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void findById_notFound() throws Exception {
        Optional<AccountDto> stubAccount = Optional.empty(); 
        when(accountService.findByIdAndName(anyString(), anyString()))
            .thenReturn(stubAccount);
        mvc.perform(get("/account/"+accountId+"/"+userId))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {})
    public void create() throws Exception {
        final AccountDto dto = new AccountDto()
                    .withAccountId(accountId)
                    .withUserId(userId)
                    .withAccountType(accountType)
                    .withBalance(balance)
                    .withAccountName(accountName);
        when(accountService.save(any(AccountDto.class)))
            .thenReturn(dto);
            mvc.perform(post("/account")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {})
    public void update_notFound() throws Exception {
        final AccountDto dto = new AccountDto()
                    .withAccountId(accountId)
                    .withUserId(userId)
                    .withAccountType(accountType)
                    .withBalance(balance)
                    .withAccountName(accountName);
        when(accountService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(put("/account")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {})
    public void update_found() throws Exception {
        final BigDecimal NEW_BALANCE = new BigDecimal("20000.00");
        final AccountDto dto = new AccountDto()
                    .withAccountId(accountId)
                    .withUserId(userId)
                    .withAccountType(accountType)
                    .withBalance(balance)
                    .withAccountName(accountName);
        when(accountService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.of(dto));
        when(accountService.update(any(AccountDto.class)))
            .thenReturn(dto.withBalance(NEW_BALANCE));
        mvc.perform(put("/account")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void deleteAccount_found() throws Exception {
        when(accountService.findByIdAndName(anyString(), anyString()))
            .thenReturn(Optional.of(new AccountDto()));
        mvc.perform(delete("/account")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = {})
    public void deleteAccount_notFound() throws Exception {
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(delete("/account")).andExpect(status().isMethodNotAllowed());
    }
  
}
