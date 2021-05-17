package com.secure.taction.SeniorProject.dtos.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BudgetItemToDtoTest {
    @InjectMocks
    private BudgetItemToDto converter;    

    @Test
    public void convert() {
        final String budgetId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String cardId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal maxBudgetBalance = new BigDecimal("10000.00");
        final BigDecimal currentBudgetBalance = new BigDecimal("7500.00");
        final BigDecimal minimumAlert = new BigDecimal("250");
        final BigDecimal autoCancel = new BigDecimal("1000");
        final String budgetName = "BudgetName";
        Budget budget = new Budget()
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

        BudgetDto result = converter.convert(budget);
        assertNotNull(result);
        assertEquals(budgetId, result.getBudgetId());
        assertEquals(userId, result.getUserId());
        assertEquals(cardId, result.getCardId());
        assertEquals(budgetName, result.getBudgetName());
        assertEquals(currentBudgetBalance, result.getCurrentBudgetBalance());
        assertEquals(maxBudgetBalance, result.getMaxBudgetBalance());
        assertEquals(minimumAlert, result.getMinimumAlert());
        assertEquals(autoCancel, result.getAutoCancel());
    }
}
