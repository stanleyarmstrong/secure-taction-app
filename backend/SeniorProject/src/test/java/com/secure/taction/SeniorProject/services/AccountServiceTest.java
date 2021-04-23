package com.secure.taction.SeniorProject.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDto;
import com.secure.taction.SeniorProject.dtos.accounts.AccountDtoToAccountItem;
import com.secure.taction.SeniorProject.dtos.accounts.AccountItemToAccountDto;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.repositories.AccountRepository;
import com.secure.taction.SeniorProject.services.AccountService;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountDtoToAccountItem dtoToItem;
    @Mock
    private AccountItemToAccountDto itemToDto;

    @Test
    public void findByIdAndUserId_empty() throws Exception {
        final String ID = "bogus";
        final String USER_ID = "bogus";
        GetItemSpec argSpec = new GetItemSpec()
                .withPrimaryKey(AccountTableConstants.ACCOUNT_ID, ID,
                                AccountTableConstants.USER_ID, USER_ID);
        Optional<AccountDto> result = accountService.findByIdAndName(anyString(), anyString());
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void findByIdandUserId_found() throws Exception {
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String accountType = "savings";
        final BigDecimal balance = new BigDecimal("10000.00");
        final String accountName = accountId + "' s " + accountType;

        final AccountDto dto = new AccountDto()
                    .withAccountId(accountId)
                    .withUserId(userId)
                    .withAccountType(accountType)
                    .withBalance(balance)
                    .withAccountName(accountName);
        final Account account = new Account()
            .withItem(
            new Item()
                    .with(AccountTableConstants.ACCOUNT_ID, accountId)
                    .with(AccountTableConstants.USER_ID, userId)
                    .with(AccountTableConstants.ACCOUNT_TYPE, accountType)
                    .with(AccountTableConstants.BALANCE, balance)
                    .with(AccountTableConstants.ACCOUNT_NAME, accountName)
            );

        when(accountRepository.findByIdAndName(any(GetItemSpec.class)))
            .thenReturn(account);
        when(itemToDto.convert(any(Account.class)))
            .thenReturn(dto);

        Optional<AccountDto> result = accountService.findByIdAndName(accountId, userId);
        assertTrue(result.isPresent());
        assertEquals(accountId, result.get().getAccountId());
        assertEquals(userId, result.get().getUserId());
        assertEquals(accountType, result.get().getAccountType());
        assertEquals(balance, result.get().getBalance());
        assertEquals(accountName, result.get().getAccountName());
    }

    @Test
    public void save() throws Exception {
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String accountType = "savings";
        final BigDecimal balance = new BigDecimal("10000.00");
        final String accountName = accountId + "' s " + accountType;

        final AccountDto dto = new AccountDto()
                    .withAccountId(accountId)
                    .withUserId(userId)
                    .withAccountType(accountType)
                    .withBalance(balance)
                    .withAccountName(accountName);
        final Account account = new Account()
            .withItem(
            new Item()
                    .with(AccountTableConstants.ACCOUNT_ID, accountId)
                    .with(AccountTableConstants.USER_ID, userId)
                    .with(AccountTableConstants.ACCOUNT_TYPE, accountType)
                    .with(AccountTableConstants.BALANCE, balance)
                    .with(AccountTableConstants.ACCOUNT_NAME, accountName)
            );

        when(accountRepository.findByIdAndName(any(GetItemSpec.class)))
            .thenReturn(account);
        when(itemToDto.convert(any(Account.class)))
            .thenReturn(dto);

        AccountDto result = accountService.save(dto);
        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
        assertEquals(userId, result.getUserId());
        assertEquals(accountType, result.getAccountType());
        assertEquals(balance, result.getBalance());
        assertEquals(accountName, result.getAccountName());
    }

    @Test
    public void update() throws Exception {
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        final String accountType = "savings";
        final BigDecimal balance = new BigDecimal("10000.00");
        final String accountName = accountId + "' s " + accountType;
        final BigDecimal NEW_BALANCE = new BigDecimal("20000.00");

        final AccountDto dto = new AccountDto()
                    .withAccountId(accountId)
                    .withUserId(userId)
                    .withAccountType(accountType)
                    .withBalance(NEW_BALANCE)
                    .withAccountName(accountName);
        final Account account = new Account()
            .withItem(
            new Item()
                    .with(AccountTableConstants.ACCOUNT_ID, accountId)
                    .with(AccountTableConstants.USER_ID, userId)
                    .with(AccountTableConstants.ACCOUNT_TYPE, accountType)
                    .with(AccountTableConstants.BALANCE, balance)
                    .with(AccountTableConstants.ACCOUNT_NAME, accountName)
            );

        when(accountRepository.findByIdAndName(any(GetItemSpec.class)))
            .thenReturn(account);
        when(itemToDto.convert(any(Account.class)))
            .thenReturn(dto);

        AccountDto result = accountService.update(dto);
        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
        assertEquals(userId, result.getUserId());
        assertEquals(accountType, result.getAccountType());
        assertEquals(NEW_BALANCE, result.getBalance());
        assertEquals(accountName, result.getAccountName());
    }

    @Test
    public void deleteByIdandUserId() throws Exception {
        final String accountId = UUID.randomUUID().toString().toUpperCase();
        final String userId = UUID.randomUUID().toString().toUpperCase();
        accountService.deleteByIdAndName(accountId, userId);
        verify(accountRepository, times(1)).deleteByIdAndName(any(DeleteItemSpec.class));
    }
}
