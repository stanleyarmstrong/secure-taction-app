package com.secure.taction.SeniorProject.services;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDtoToItem;
import com.secure.taction.SeniorProject.dtos.budget.BudgetItemToDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.utils.QueryUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetDtoToItem dtoToItem;
    private final BudgetItemToDto itemToDto;
    private final UserService userService;
    private final UserItemToUserDto userItemToDto;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository,
                         UserRepository userRepository,
                         BudgetDtoToItem dtoToItem,
                         BudgetItemToDto itemToDto,
                         UserService userService,
                         UserItemToUserDto userItemToDto) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
        this.userService = userService;
        this.userItemToDto = userItemToDto;
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

        BudgetDto toReturn = itemToDto.convert(
                    budgetRepository.save(budget));
        updateUser(toReturn.getUserId(), toReturn.getBudgetId());
        return toReturn;

    }

    private void updateUser(String userId, String budgetId) {
        QuerySpec userQuerySpec = QueryUtils.userQuerySpec(userId);
        ItemCollection<QueryOutcome> userItem = userRepository.queryForUser(userQuerySpec);
        // Expecting it to be one item
        Iterator<Item> iterator = userItem.iterator();
        UserDto toUpdate = userItemToDto.convert(new User().withItem(iterator.next()));
        userRepository.update(toUpdate.addBudget(budgetId));
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

        ItemCollection<QueryOutcome> userCollection = userRepository.queryForUser(QueryUtils.userQuerySpec(userId));
        UserDto userDto = userItemToDto.convert(new User().withItem(userCollection.iterator().next()));
        userDto.removeBudget(id);
        try {
            userService.update(userDto);
            budgetRepository.deleteByIdAndUserId(spec);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
