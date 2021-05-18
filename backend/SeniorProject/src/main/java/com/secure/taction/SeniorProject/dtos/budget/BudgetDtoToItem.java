package com.secure.taction.SeniorProject.dtos.budget;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BudgetDtoToItem implements Converter<BudgetDto, Budget> {

    @Override
    public Budget convert(BudgetDto dto) {
        return new Budget().withItem(
            new Item()
                .with(BudgetTableConstants.BUDGET_ID, UUID.randomUUID().toString().toUpperCase())
                .with(BudgetTableConstants.USER_ID, dto.getUserId())
                .with(BudgetTableConstants.ACCOUNT_ID, dto.getAccountId())
                .with(BudgetTableConstants.MAX_BALANCE, dto.getMaxBudgetBalance())
                .with(BudgetTableConstants.CUR_BALANCE, dto.getCurrentBudgetBalance())
                .with(BudgetTableConstants.MIN_ALERT, dto.getMinimumAlert())
                .with(BudgetTableConstants.AUTO_CANCEL, dto.getAutoCancel())
                .with(BudgetTableConstants.BUDGET_NAME, dto.getBudgetName())
        );
    }
    
}
