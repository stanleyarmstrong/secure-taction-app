package com.secure.taction.SeniorProject.models;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
public class Account {

  private Item accountItem;

  public Account() {}

  public Item getItem() { return accountItem;}
  
  public void setItem(Item accountItem) { this.accountItem = accountItem;}

  public Account withItem(Item accountItem) { 
    setItem(accountItem);
    return this;
  }

  public Map<String, Object> getAttributes() {
    return accountItem.asMap();
  }

  //Debugging method
  public Account withAccountId(String accountId) {
    accountItem.withPrimaryKey(AccountTableConstants.ACCOUNT_ID, accountId);
    return this;
  }
  
}
