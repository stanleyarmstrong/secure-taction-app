package com.secure.taction.SeniorProject.dtos.accounts;

import java.math.BigDecimal;
import java.util.Map;

import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountItemToAccountDto implements Converter<Account, AccountDto>{

  @Override
  public AccountDto convert(Account account) {
    Map<String, Object> argItem = account.getAttributes();
    return new AccountDto()
      .withAccountId((String) argItem.get(AccountTableConstants.ACCOUNT_ID))     
      .withUserId((String) argItem.get(AccountTableConstants.USER_ID))
      .withAccountType((String) argItem.get(AccountTableConstants.ACCOUNT_TYPE))
      .withBalance((BigDecimal) argItem.get(AccountTableConstants.BALANCE))
      .withAccountName((String) argItem.get(AccountTableConstants.ACCOUNT_NAME));
  }
}
