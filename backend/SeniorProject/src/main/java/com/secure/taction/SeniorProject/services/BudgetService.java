package com.secure.taction.SeniorProject.services;

import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDtoToItem;
import com.secure.taction.SeniorProject.dtos.budget.BudgetItemToDto;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    
    private final BudgetRepository budgetRepository;
    private final BudgetDtoToItem dtoToItem;
    private final BudgetItemToDto itemToDto;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository,
                         BudgetDtoToItem dtoToItem,
                         BudgetItemToDto itemToDto) {
        this.budgetRepository = budgetRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
    }

    public Optional<BudgetDto> findByIdAndUserId(@NonNull String id, 
                                                 @NonNull String userId) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(BudgetTableConstants.BUDGET_ID, id,
                                                            BudgetTableConstants.USER_ID, userId);
        Optional<BudgetDto> toReturn = Optional.empty();
        try {
            Budget budget = budgetRepository.findByIdAndUserId(spec);
            if (Objects.nonNull(budget.getItem()))
                toReturn = Optional.of(itemToDto.convert(budget));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return toReturn;
        }
        return toReturn;
    }

    public BudgetDto save(BudgetDto budgetDto) {
        Budget budget = dtoToItem.convert(budgetDto);
        return itemToDto.convert(
                    budgetRepository.save(budget));
    }

    public BudgetDto update(BudgetDto budgetDto) {
        return itemToDto.convert(
                    budgetRepository.update(budgetDto))
                    .withBudgetId(budgetDto.getBudgetId())
                    .withUserId(budgetDto.getUserId());
    }

    public void deleteByIdAndUserId(@NonNull String id, 
                                    @NonNull String userId) {
        DeleteItemSpec spec = new DeleteItemSpec()
                                .withPrimaryKey(BudgetTableConstants.BUDGET_ID, id,
                                                BudgetTableConstants.USER_ID, userId);
        try {
            budgetRepository.deleteByIdAndUserId(spec);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
