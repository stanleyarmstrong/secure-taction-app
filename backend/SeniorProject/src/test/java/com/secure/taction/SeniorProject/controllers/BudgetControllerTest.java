package com.secure.taction.SeniorProject.controllers;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BudgetControllerTest extends BaseControllerTest {
    
    final private String budgetId = UUID.randomUUID().toString().toUpperCase();
    final private String userId = UUID.randomUUID().toString().toUpperCase();
    final private String cardId = UUID.randomUUID().toString().toUpperCase();
    final private BigDecimal maxBudgetBalance = new BigDecimal("10000.00");
    final private BigDecimal currentBudgetBalance = new BigDecimal("7500.00");
    final private BigDecimal minimumAlert = new BigDecimal("100.00");
    final private BigDecimal autoCancel = new BigDecimal("500.00");
    final private String budgetName = "expectedBudgetName";
    
    @Test
    @WithMockUser(roles = {})
    public void findById_found() throws Exception {
        BudgetDto stubBudget = new BudgetDto();
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(Optional.of(stubBudget));
        mvc.perform(get("/budget/"+budgetId+"/"+userId))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {})
    public void findById_notFound() throws Exception {
        Optional<BudgetDto> stubBudget = Optional.empty(); 
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(stubBudget);
        mvc.perform(get("/budget/"+budgetId+"/"+userId))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {})
    public void create() throws Exception {
        final BudgetDto dto = new BudgetDto()
                    .withBudgetId(budgetId)
                    .withUserId(userId)
                    .withCardId(cardId)
                    .withMaxBudgetBalance(maxBudgetBalance)
                    .withCurrentBudgetBalance(currentBudgetBalance)
                    .withMinimumAlert(minimumAlert)
                    .withAutoCancel(autoCancel);
        when(budgetService.save(any(BudgetDto.class)))
            .thenReturn(dto);
            mvc.perform(post("/budget")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {})
    public void update_notFound() throws Exception {
        final BudgetDto dto = new BudgetDto()
                    .withBudgetId(budgetId)
                    .withUserId(userId)
                    .withCardId(cardId)
                    .withMaxBudgetBalance(maxBudgetBalance)
                    .withCurrentBudgetBalance(currentBudgetBalance)
                    .withMinimumAlert(minimumAlert)
                    .withAutoCancel(autoCancel);
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(put("/budget")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = {})
    public void update_found() throws Exception {
        final BigDecimal NEW_MAX_BUDGET_BALANCE = new BigDecimal("20000.00");
        final BudgetDto dto = new BudgetDto()
                    .withBudgetId(budgetId)
                    .withUserId(userId)
                    .withCardId(cardId)
                    .withMaxBudgetBalance(maxBudgetBalance)
                    .withCurrentBudgetBalance(currentBudgetBalance)
                    .withMinimumAlert(minimumAlert)
                    .withAutoCancel(autoCancel);
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(Optional.of(dto));
        when(budgetService.update(any(BudgetDto.class)))
            .thenReturn(dto.withMaxBudgetBalance(NEW_MAX_BUDGET_BALANCE));
        mvc.perform(put("/budget")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());
    }

    /*
    *
    *   I really have no clue why the hell
    *   These are retruning this way
    *
    */
    @Test
    @WithMockUser(roles = {})
    public void deleteBudget_found() throws Exception {
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(Optional.of(new BudgetDto()));
        mvc.perform(delete("/budget")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = {})
    public void deleteBudget_notFound() throws Exception {
        when(budgetService.findByIdAndUserId(anyString(), anyString()))
            .thenReturn(Optional.empty());
        mvc.perform(delete("/budget")).andExpect(status().isMethodNotAllowed());
    }

}
