package com.secure.taction.SeniorProject.dtos.accounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Map;

import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class AccountDtoToItemTest {
  @InjectMocks
  private AccountDtoToAccountItem converter;

  @Test
  public void convert() {
    final String accountId = UUID.randomUUID().toString().toUpperCase();
    final String userId = UUID.randomUUID().toString().toUpperCase();
    final String accountType = "checking";
    final BigDecimal balance = new BigDecimal("1000");
    final String accountName = userId + "\'s checking account";

    final AccountDto account = new AccountDto()
      .withAccountId(accountId)
      .withUserId(userId)
      .withAccountType(accountType)
      .withBalance(balance)
      .withAccountName(accountName);
    Account result = converter.convert(account);
    assertNotNull(result);
    Map<String, Object> map = result.getAttributes();
    assertTrue(MapUtils.isNotEmpty(map));
    assertEquals(accountId, (String) map.get(AccountTableConstants.ACCOUNT_ID));
    assertEquals(userId, (String) map.get(AccountTableConstants.USER_ID));
    assertEquals(accountType, (String) map.get(AccountTableConstants.ACCOUNT_TYPE));
    assertEquals(balance, new BigDecimal(((Number) map.get(AccountTableConstants.BALANCE)).toString()));
    assertEquals(accountName, (String) map.get(AccountTableConstants.ACCOUNT_NAME));
  }
  
}

