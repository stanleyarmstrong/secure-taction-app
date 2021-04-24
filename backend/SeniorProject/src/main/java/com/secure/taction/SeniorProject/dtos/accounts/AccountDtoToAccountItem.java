package com.secure.taction.SeniorProject.dtos.accounts;

import java.util.UUID;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.models.Account;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoToAccountItem implements Converter<AccountDto, Account>{
  @Override
  public Account convert(AccountDto dto) {
  
    return new Account().withItem(
      new Item()
        .withPrimaryKey(AccountTableConstants.ACCOUNT_ID, UUID.randomUUID().toString().toUpperCase())
        .with(AccountTableConstants.USER_ID, dto.getUserId())
        .with(AccountTableConstants.ACCOUNT_TYPE, dto.getAccountType())
        .with(AccountTableConstants.BALANCE, dto.getBalance())
        .with(AccountTableConstants.ACCOUNT_NAME, dto.getAccountName())
      );
  } 
}
