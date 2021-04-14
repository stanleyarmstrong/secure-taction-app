package com.secure.taction.SeniorProject.dtos.budget;

import java.math.BigDecimal;
import java.util.Map;

import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.springframework.core.convert.converter.Converter;

public class BudgetItemToDto implements Converter<Budget, BudgetDto> {

    @Override
    public BudgetDto convert(Budget source) {
        Map<String, Object> item = source.getAttributes();
        return new BudgetDto()
            .withBudgetId((String) item.get(BudgetTableConstants.BUDGET_ID))
            .withUserId((String) item.get(BudgetTableConstants.USER_ID))
            .withCardId((String) item.get(BudgetTableConstants.CARD_ID))
            .withMaxBudgetBalance(new BigDecimal(((Number) item.get(BudgetTableConstants.MAX_BALANCE)).toString()))
            .withCurrentBudgetBalance(new BigDecimal(((Number) item.get(BudgetTableConstants.CUR_BALANCE)).toString()))
            .withMinimumAlert(new BigDecimal(((Number) item.get(BudgetTableConstants.MIN_ALERT)).toString()))
            .withAutoCancel(new BigDecimal(((Number) item.get(BudgetTableConstants.AUTO_CANCEL)).toString()))
            .withBudgetName((String) item.get(BudgetTableConstants.BUDGET_NAME))
        ;
    }
    
}
