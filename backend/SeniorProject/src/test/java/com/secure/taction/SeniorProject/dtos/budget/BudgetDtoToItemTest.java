package com.secure.taction.SeniorProject.dtos.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BudgetDtoToItemTest {

    @InjectMocks
    private BudgetDtoToItem converter;

    @Test
    public void convert() {
        final String budgetId = "String";
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String cardId = UUID.randomUUID().toString().toUpperCase();
        final BigDecimal maxBudgetBalance = new BigDecimal("10000.00");
        final BigDecimal currentBudgetBalance = new BigDecimal("7500.00");
        final BigDecimal minimumAlert = new BigDecimal("250");
        final BigDecimal autoCancel = new BigDecimal("1000");
        final String budgetName = "BudgetName";

        final BudgetDto dto = new BudgetDto()
                .withBudgetId(budgetId)
                .withUserId(userId)
                .withAccountId(cardId)
                .withMaxBudgetBalance(maxBudgetBalance)
                .withCurrentBudgetBalance(currentBudgetBalance)
                .withMinimumAlert(minimumAlert)
                .withAutoCancel(autoCancel)
                .withBudgetName(budgetName);

        Budget result = converter.convert(dto);
        assertNotNull(result);
        Map<String, Object> map = result.getAttributes();
        assertTrue(MapUtils.isNotEmpty(map));
        assertEquals(userId, (String) map.get(BudgetTableConstants.USER_ID));
        assertEquals(cardId, (String) map.get(BudgetTableConstants.ACCOUNT_ID));
        assertEquals(budgetName, (String) map.get(BudgetTableConstants.BUDGET_NAME));
        assertEquals(maxBudgetBalance, new BigDecimal(((Number) map.get(BudgetTableConstants.MAX_BALANCE)).toString()));
        assertEquals(currentBudgetBalance, new BigDecimal(((Number) map.get(BudgetTableConstants.CUR_BALANCE)).toString()));
        assertEquals(minimumAlert, new BigDecimal(((Number) map.get(BudgetTableConstants.MIN_ALERT)).toString()));
        assertEquals(autoCancel, new BigDecimal(((Number) map.get(BudgetTableConstants.AUTO_CANCEL)).toString()));
    }
}
