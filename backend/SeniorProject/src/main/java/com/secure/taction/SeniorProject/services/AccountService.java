package com.secure.taction.SeniorProject.services;

import java.util.Objects;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDtoToAccountItem;
import com.secure.taction.SeniorProject.dtos.accounts.AccountItemToAccountDto;
import com.secure.taction.SeniorProject.dtos.user.UserDto;
import com.secure.taction.SeniorProject.dtos.user.UserItemToUserDto;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.models.User;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.repositories.BudgetRepository;
import com.secure.taction.SeniorProject.repositories.UserRepository;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.utils.QueryUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoToAccountItem dtoToItem;
    private final AccountItemToAccountDto itemToDto;
    private final UserRepository userRepository;
    private final UserItemToUserDto userItemToDto;


    @Autowired
    public AccountService(AccountRepository accountRepository,
                       AccountDtoToAccountItem dtoToItem,
                       AccountItemToAccountDto itemToDto,
                       UserRepository userRepository,
                       UserItemToUserDto userItemToDto) {
        this.accountRepository = accountRepository;
        this.dtoToItem = dtoToItem;
        this.itemToDto = itemToDto;
        this.userRepository = userRepository;
        this.userItemToDto = userItemToDto;
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
        ItemCollection<QueryOutcome> userCollection = userRepository.queryForUser(QueryUtils.userQuerySpec(userId));
        UserDto userDto = userItemToDto.convert(new User().withItem(userCollection.iterator().next()));
        userDto.removeAccount(id);
        try {
            accountRepository.deleteByIdAndName(spec);
            userRepository.update(userDto);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
