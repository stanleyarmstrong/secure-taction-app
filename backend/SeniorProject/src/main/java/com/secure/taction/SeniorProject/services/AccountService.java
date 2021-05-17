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
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDtoToAccountItem;
import com.secure.taction.SeniorProject.dtos.accounts.AccountItemToAccountDto;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final BudgetRepository budgetRepository;
    private final AccountDtoToAccountItem dtoToItem;
    private final AccountItemToAccountDto itemToDto;


    @Autowired
    public AccountService(AccountRepository accountRepository,
                          BudgetRepository budgetRepository,
                       AccountDtoToAccountItem dtoToItem,
                       AccountItemToAccountDto itemToDto) {
        this.accountRepository = accountRepository;
        this.budgetRepository = budgetRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
    }



    public Optional<AccountDto> findByIdAndName(@NonNull String id, @NonNull String userId) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey(AccountTableConstants.ACCOUNT_ID, id,
                                                            AccountTableConstants.USER_ID, userId);
        Optional<AccountDto> toReturn = Optional.empty();
        try {
            Account account = accountRepository.findByIdAndName(spec);
            if (Objects.nonNull(account.getItem()))
                toReturn = Optional.of(itemToDto.convert(account));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return toReturn;
        }
        return toReturn;
        
    }

    public AccountDto save(AccountDto accountDto) {
        Account account = dtoToItem.convert(accountDto);
        return itemToDto.convert(
                accountRepository.save(account));
    }

    public AccountDto update(AccountDto accountDto) {
        return itemToDto.convert(
                accountRepository.update(accountDto))
                .withAccountId(accountDto.getAccountId())
                .withUserId(accountDto.getUserId());
                
    }

    public void deleteByIdAndName(@NonNull String id, @NonNull String userId) {
        DeleteItemSpec spec = new DeleteItemSpec().withPrimaryKey(AccountTableConstants.ACCOUNT_ID, id,
                                                                AccountTableConstants.USER_ID, userId);
        try {
            accountRepository.deleteByIdAndName(spec);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public AccountAndBudgetDto findAccountWithBudgets(String userId) {
        AccountAndBudgetDto toReturn = new AccountAndBudgetDto(); 
        QuerySpec accountQuerySpec = accountQuerySpec(userId);
        ItemCollection<QueryOutcome> accountItems = accountRepository.queryForAccount(accountQuerySpec);

    /*
        QuerySpec budgetQuerySpec = budgetQuerySpec(userId);
        ItemCollection<QueryOutcome> budgetItems = budgetRepository.queryForBudget(budgetQuerySpec);
    */
        Iterator<Item> iterator = accountItems.iterator();
        System.out.println("Testing Account Item Print");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
    /*
        iterator = budgetItems.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJSONPretty());
        }
    */
        return new AccountAndBudgetDto();
    }

    private QuerySpec accountQuerySpec(String userId) { 
        return new QuerySpec()
            .withKeyConditionExpression(AccountTableConstants.USER_ID +" = :accountId")
            .withValueMap(new ValueMap()
                .withString(":accountId", userId)
            ).withConsistentRead(true);
    }

    private QuerySpec budgetQuerySpec(String accountId) {
        return new QuerySpec()
            .withKeyConditionExpression(BudgetTableConstants.ACCOUNT_ID +" = :accountId")
            .withValueMap(new ValueMap()
                .withString(":accountId", accountId)
            ).withConsistentRead(true);
    }
}
