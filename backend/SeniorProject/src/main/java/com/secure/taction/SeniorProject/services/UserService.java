package com.secure.taction.SeniorProject.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.secure.taction.SeniorProject.dtos.accounts.AccountAndBudgetDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDtoToAccountItem;
import com.secure.taction.SeniorProject.dtos.accounts.AccountItemToAccountDto;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDtoToItem;
import com.secure.taction.SeniorProject.dtos.budget.BudgetItemToDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserDtoToUserItem;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;
import com.secure.taction.SeniorProject.utils.QueryUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final BudgetRepository budgetRepository;
    private final UserDtoToUserItem dtoToItem;
    private final UserItemToUserDto itemToDto;
    private final BudgetDtoToItem budgetDtoToItem;
    private final BudgetItemToDto budgetItemToDto;
    private final AccountDtoToAccountItem accountDtoToItem;
    private final AccountItemToAccountDto accountItemToDto;

    @Autowired
    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository, 
                       BudgetRepository budgetRepository,
                       UserDtoToUserItem dtoToItem,
                       UserItemToUserDto itemToDto,
                       BudgetItemToDto budgetItemToDto,
                       BudgetDtoToItem budgetDtoToItem,
                       AccountDtoToAccountItem accountDtoToItem,
                       AccountItemToAccountDto accountItemToDto) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.budgetRepository = budgetRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
        this.budgetDtoToItem = budgetDtoToItem;
        this.budgetItemToDto = budgetItemToDto;
        this.accountDtoToItem = accountDtoToItem;
        this.accountItemToDto = accountItemToDto;
    }



    public Optional<UserDto> findByIdAndName(@NonNull String id, @NonNull String username) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(UserTableConstants.USER_ID, id,
                                                            UserTableConstants.USER_NAME, username);
        Optional<UserDto> toReturn = Optional.empty();
        try {
            User user = userRepository.findByIdAndName(spec);
            if (Objects.nonNull(user.getItem()))
                toReturn = Optional.of(itemToDto.convert(user));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return toReturn;
        }
        return toReturn;
        
    }

    public UserDto save(UserDto userDto) {
        User user = dtoToItem.convert(userDto);
        return itemToDto.convert(
                userRepository.save(user));
    }

    public UserDto update(UserDto userDto) {
        return itemToDto.convert(
                userRepository.update(userDto))
                .withUserId(userDto.getUserId())
                .withUserName(userDto.getUserName());
                
    }

    public void deleteByIdAndName(@NonNull String id, @NonNull String username) {
        DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey(UserTableConstants.USER_ID, id,
                                                                UserTableConstants.USER_NAME, username);
        try {
            userRepository.deleteByIdAndName(spec);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<AccountAndBudgetDto> findAccountWithBudgets(String userId) {
        List<AccountAndBudgetDto> toReturn = new LinkedList<>();
        Map<String, AccountDto> accountToAccountIdMapping = new HashMap<>();
        List<BudgetDto> budgetDtos = new LinkedList<>();
        QuerySpec userQuerySpec = QueryUtils.userQuerySpec(userId);
        ItemCollection<QueryOutcome> userItem = userRepository.queryForUser(userQuerySpec);
        Iterator<Item> iterator = userItem.iterator();
        UserDto sourceUser = itemToDto.convert(new User().withItem(iterator.next()));
        for (String accountId : sourceUser.getAccounts()) {
            QuerySpec accountQuerySpec = QueryUtils.accountQuerySpec(accountId);
            ItemCollection<QueryOutcome> accountItem = accountRepository.queryForAccount(accountQuerySpec);
            iterator = accountItem.iterator();
            if (iterator.hasNext() == false) continue;
            AccountDto accountDto = accountItemToDto.convert(new Account().withItem(iterator.next()));
            accountToAccountIdMapping.put(accountDto.getAccountId(), accountDto);
        }
        for (String budgetId : sourceUser.getBudgets()) {
            QuerySpec budgetQuerySpec = QueryUtils.budgetQuerySpec(budgetId);
            ItemCollection<QueryOutcome> budgetItem = budgetRepository.queryForBudget(budgetQuerySpec);
            iterator = budgetItem.iterator();
            if (iterator.hasNext() == false) continue;
            BudgetDto budgetDto = budgetItemToDto.convert(new Budget().withItem(iterator.next()));
            budgetDtos.add(budgetDto);
        }
        for (BudgetDto budgetDto : budgetDtos) {
            AccountDto toMapWith = accountToAccountIdMapping.get(budgetDto.getAccountId());
            AccountAndBudgetDto toAdd = new AccountAndBudgetDto()
                    .withAccountName(toMapWith.getAccountName())
                    .withAccountId(toMapWith.getAccountId())
                    .withAccountBalance(toMapWith.getBalance())
                    .withBudgetName(budgetDto.getBudgetName())
                    .withCurrentBudgetBalance(budgetDto.getCurrentBudgetBalance())
                    .withMaxBudgetBalance(budgetDto.getMaxBudgetBalance())
                    .withAutoCancel(budgetDto.getAutoCancel())
                    .withMinimumAlert(budgetDto.getMinimumAlert());
            toReturn.add(toAdd);
        }

        return toReturn;
    }



    private Map<String, BudgetDto> initializeMappings(List<String> accounts) {
        Map<String, BudgetDto> toReturn = new HashMap<>();
        accounts.forEach(s -> toReturn.put(s, null));
        return toReturn;
    }


}
