package com.secure.taction.SeniorProject.dtos.accounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountItemToDtoTest {
  @InjectMocks
  private AccountItemToAccountDto converter;

  @Test
  public void convert() {
    final String accountId = UUID.randomUUID().toString().toUpperCase();
    final String userId = UUID.randomUUID().toString().toUpperCase();
    final String accountType = "checking";
    final BigDecimal balance = new BigDecimal("1000");
    final String accountName = userId + "\'s checking account";

    Account account = new Account()
      .withItem(
        new Item()
          .with(AccountTableConstants.ACCOUNT_ID, accountId)
          .with(AccountTableConstants.USER_ID, userId)
          .with(AccountTableConstants.ACCOUNT_TYPE, accountType)
          .with(AccountTableConstants.BALANCE, balance)
          .with(AccountTableConstants.ACCOUNT_NAME, accountName)
      );
    AccountDto result = converter.convert(account);
    assertNotNull(result);
    assertEquals(accountId, result.getAccountId());
    assertEquals(userId, result.getUserId());
    assertEquals(accountType, result.getAccountType());
    assertEquals(balance, result.getBalance());
    assertEquals(accountName, result.getAccountName());
  }
  
}
