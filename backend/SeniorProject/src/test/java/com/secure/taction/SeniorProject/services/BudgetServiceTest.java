package com.secure.taction.SeniorProject.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDtoToItem;
import com.secure.taction.SeniorProject.dtos.budget.BudgetItemToDto;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.services.BudgetService;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BudgetServiceTest {
    @InjectMocks
    private BudgetService budgetService;
    @Mock
    private BudgetRepository budgetRepository;
    @Mock
    private BudgetDtoToItem dtoToItem;
    @Mock
    private BudgetItemToDto itemToDto;

    @Test
    public void findByIdAndUserId_empty() throws Exception {
        final String ID = "bogus";
        final String USER_ID = "bogus";
        GetItemSpec argSpec = new GetItemSpec()
                .withPrimaryKey(BudgetTableConstants.BUDGET_ID, ID,
                                BudgetTableConstants.USER_ID, USER_ID);
        Optional<BudgetDto> result = budgetService.findByIdAndUserId(anyString(), anyString());
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void findByIdandUserId_found() throws Exception {
        final String budgetId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String cardId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal maxBudgetBalance = new BigDecimal("10000.00");
        final BigDecimal currentBudgetBalance = new BigDecimal("7500.00");
        final BigDecimal minimumAlert = new BigDecimal("100.00");
        final BigDecimal autoCancel = new BigDecimal("500.00");
        final String budgetName = "expectedBudgetName";

        final BudgetDto dto = new BudgetDto()
                    .withBudgetId(budgetId)
                    .withUserId(userId)
                    .withCardId(cardId)
                    .withMaxBudgetBalance(maxBudgetBalance)
                    .withCurrentBudgetBalance(currentBudgetBalance)
                    .withMinimumAlert(minimumAlert)
                    .withAutoCancel(autoCancel)
                    .withBudgetName(budgetName);
        final Budget budget = new Budget()
            .withItem(
            new Item()
                    .with(BudgetTableConstants.BUDGET_ID, budgetId)
                    .with(BudgetTableConstants.USER_ID, userId)
                    .with(BudgetTableConstants.ACCOUNT_ID, cardId)
                    .with(BudgetTableConstants.MAX_BALANCE, maxBudgetBalance)
                    .with(BudgetTableConstants.CUR_BALANCE, currentBudgetBalance)
                    .with(BudgetTableConstants.MIN_ALERT, minimumAlert)
                    .with(BudgetTableConstants.AUTO_CANCEL, autoCancel)
                    .with(BudgetTableConstants.BUDGET_NAME, budgetName)
            );

        when(budgetRepository.findByIdAndUserId(any(GetItemSpec.class)))
            .thenReturn(budget);
        when(itemToDto.convert(any(Budget.class)))
            .thenReturn(dto);

        Optional<BudgetDto> result = budgetService.findByIdAndUserId(budgetId, userId);
        assertTrue(result.isPresent());
        assertEquals(budgetId, result.get().getBudgetId());
        assertEquals(userId, result.get().getUserId());
        assertEquals(cardId, result.get().getCardId());
        assertEquals(maxBudgetBalance, result.get().getMaxBudgetBalance());
        assertEquals(currentBudgetBalance, result.get().getCurrentBudgetBalance());
        assertEquals(autoCancel, result.get().getAutoCancel());
        assertEquals(minimumAlert, result.get().getMinimumAlert());
        assertEquals(budgetName, result.get().getBudgetName());
    }

    @Test
    public void save() throws Exception {
        final String budgetId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String cardId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal maxBudgetBalance = new BigDecimal("10000.00");
        final BigDecimal currentBudgetBalance = new BigDecimal("7500.00");
        final BigDecimal minimumAlert = new BigDecimal("100.00");
        final BigDecimal autoCancel = new BigDecimal("500.00");
        final String budgetName = "expectedBudgetName";

        final BudgetDto dto = new BudgetDto()
                    .withBudgetId(budgetId)
                    .withUserId(userId)
                    .withCardId(cardId)
                    .withMaxBudgetBalance(maxBudgetBalance)
                    .withCurrentBudgetBalance(currentBudgetBalance)
                    .withMinimumAlert(minimumAlert)
                    .withAutoCancel(autoCancel)
                    .withBudgetName(budgetName);
        final Budget budget = new Budget()
            .withItem(
            new Item()
                    .with(BudgetTableConstants.BUDGET_ID, budgetId)
                    .with(BudgetTableConstants.USER_ID, userId)
                    .with(BudgetTableConstants.ACCOUNT_ID, cardId)
                    .with(BudgetTableConstants.MAX_BALANCE, maxBudgetBalance)
                    .with(BudgetTableConstants.CUR_BALANCE, currentBudgetBalance)
                    .with(BudgetTableConstants.MIN_ALERT, minimumAlert)
                    .with(BudgetTableConstants.AUTO_CANCEL, autoCancel)
                    .with(BudgetTableConstants.BUDGET_NAME, budgetName)
            );


        when(itemToDto.convert(budget))
            .thenReturn(dto);
        when(dtoToItem.convert(dto))
            .thenReturn(budget);
        when(budgetRepository.save(any(Budget.class)))
            .thenReturn(budget);

        BudgetDto result = budgetService.save(dto);
        assertNotNull(result);
        assertEquals(budgetId, result.getBudgetId());
        assertEquals(userId, result.getUserId());
        assertEquals(cardId, result.getCardId());
        assertEquals(maxBudgetBalance, result.getMaxBudgetBalance());
        assertEquals(currentBudgetBalance, result.getCurrentBudgetBalance());
        assertEquals(autoCancel, result.getAutoCancel());
        assertEquals(minimumAlert, result.getMinimumAlert());
        assertEquals(budgetName, result.getBudgetName());
    }

    @Test
    public void update() throws Exception {
        final String budgetId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String cardId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal maxBudgetBalance = new BigDecimal("10000.00");
        final BigDecimal currentBudgetBalance = new BigDecimal("7500.00");
        final BigDecimal minimumAlert = new BigDecimal("100.00");
        final BigDecimal autoCancel = new BigDecimal("500.00");
        final String budgetName = "expectedBudgetName";

        final BigDecimal NEW_MAX_BUDGET_BALANCE = new BigDecimal("20000.00");

        final BudgetDto dto = new BudgetDto()
                    .withBudgetId(budgetId)
                    .withUserId(userId)
                    .withCardId(cardId)
                    .withMaxBudgetBalance(NEW_MAX_BUDGET_BALANCE)
                    .withCurrentBudgetBalance(currentBudgetBalance)
                    .withMinimumAlert(minimumAlert)
                    .withAutoCancel(autoCancel)
                    .withBudgetName(budgetName);
        final Budget budget = new Budget()
            .withItem(
            new Item()
                    .with(BudgetTableConstants.BUDGET_ID, budgetId)
                    .with(BudgetTableConstants.USER_ID, userId)
                    .with(BudgetTableConstants.ACCOUNT_ID, cardId)
                    .with(BudgetTableConstants.MAX_BALANCE, maxBudgetBalance)
                    .with(BudgetTableConstants.CUR_BALANCE, currentBudgetBalance)
                    .with(BudgetTableConstants.MIN_ALERT, minimumAlert)
                    .with(BudgetTableConstants.AUTO_CANCEL, autoCancel)
                    .with(BudgetTableConstants.BUDGET_NAME, budgetName)
            );


        when(budgetRepository.update(dto))
            .thenReturn(budget);
        when(itemToDto.convert(any(Budget.class)))
            .thenReturn(dto);

        BudgetDto result = budgetService.update(dto);
        assertNotNull(result);
        assertEquals(budgetId, result.getBudgetId());
        assertEquals(userId, result.getUserId());
        assertEquals(cardId, result.getCardId());
        assertEquals(NEW_MAX_BUDGET_BALANCE, result.getMaxBudgetBalance());
        assertEquals(currentBudgetBalance, result.getCurrentBudgetBalance());
        assertEquals(autoCancel, result.getAutoCancel());
        assertEquals(minimumAlert, result.getMinimumAlert());
        assertEquals(budgetName, result.getBudgetName());
    }

    @Test
    public void deleteByIdandUserId() throws Exception {
        final String budgetId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        budgetService.deleteByIdAndUserId(budgetId, userId);
        verify(budgetRepository, times(1)).deleteByIdAndUserId(any(DeleteItemSpec.class));
    }
}
