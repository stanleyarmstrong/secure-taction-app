package com.secure.taction.SeniorProject.services;

import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDtoToAccountItem;
import com.secure.taction.SeniorProject.dtos.accounts.AccountItemToAccountDto;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoToAccountItem dtoToItem;
    private final AccountItemToAccountDto itemToDto;


    @Autowired
    public AccountService(AccountRepository accountRepository,
                       AccountDtoToAccountItem dtoToItem,
                       AccountItemToAccountDto itemToDto) {
        this.accountRepository = accountRepository;
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

}
