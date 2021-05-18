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
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.secure.taction.SeniorProject.dtos.accounts.AccountAndBudgetDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserDtoToUserItem;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

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

    @Autowired
    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository, 
                       BudgetRepository budgetRepository,
                       UserDtoToUserItem dtoToItem,
                       UserItemToUserDto itemToDto) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.budgetRepository = budgetRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
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

    public AccountAndBudgetDto findAccountWithBudgets(String userId) {
        AccountAndBudgetDto toReturn = new AccountAndBudgetDto(); 
        QuerySpec userQuerySpec = QueryUtils.userQuerySpec(userId);
        ItemCollection<QueryOutcome> userItem = userRepository.queryForUser(userQuerySpec);
        Iterator<Item> iterator = userItem.iterator();
        System.out.println("Testing Account Item Print");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
//        QuerySpec accountQuerySpec = accountQuerySpec(userId);
//        ItemCollection<QueryOutcome> accountItems = accountRepository.queryForAccount(accountQuerySpec);

    /*
        QuerySpec budgetQuerySpec = budgetQuerySpec(userId);
        ItemCollection<QueryOutcome> budgetItems = budgetRepository.queryForBudget(budgetQuerySpec);
    */
    /*
        Iterator<Item> iterator = accountItems.iterator();
        System.out.println("Testing Account Item Print");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
    */
    /*
        iterator = budgetItems.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
    */
        return toReturn;
    }

}
