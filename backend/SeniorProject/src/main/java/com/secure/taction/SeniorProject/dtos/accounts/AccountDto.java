package com.secure.taction.SeniorProject.dtos.accounts;

import java.math.BigDecimal;

import org.springframework.lang.NonNull;

public class AccountDto {
  private String accountId;
  private String userId;
  private String accountType;
  private BigDecimal balance;
  private String accountName;

  public AccountDto() {}

  public String accountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public AccountDto withAccountId(String accountId) {
    setAccountId(accountId);
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public AccountDto withUserId(String userId) {
    setUserId(userId);
    return this;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public AccountDto withAccountType(String accountType) {
    setUserId(accountType);
    return this;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public AccountDto withBalance(BigDecimal balance) {
    setBalance(balance);
    return this;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public AccountDto withAccountName(String accountName) {
    setUserId(accountName);
    return this;
  }

}