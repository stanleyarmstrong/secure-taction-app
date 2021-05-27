package com.secure.taction.SeniorProject.dtos.accounts;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;

public class AccountAndBudgetDto {

    private String accountName;
    private String accountId;
    private BigDecimal accountBalance;
    private BigDecimal maxBudgetBalance;
    private BigDecimal currentBudgetBalance;
    private BigDecimal minimumAlert;
    private BigDecimal autoCancel;
    private String budgetName;

    public AccountAndBudgetDto() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountAndBudgetDto withAccountName(String accountName) {
        setAccountName(accountName);
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountAndBudgetDto withAccountBalance(BigDecimal accountBalance) {
        setAccountBalance(accountBalance);
        return this;
    }

    public BigDecimal getMaxBudgetBalance() {
        return maxBudgetBalance;
    }

    public void setMaxBudgetBalance(BigDecimal maxBudgetBalance) {
        this.maxBudgetBalance = maxBudgetBalance;
    }

    public AccountAndBudgetDto withMaxBudgetBalance(BigDecimal maxBudgetBalance) {
        setMaxBudgetBalance(maxBudgetBalance);
        return this;
    }

    public BigDecimal getCurrentBudgetBalance() {
        return currentBudgetBalance;
    }

    public void setCurrentBudgetBalance(BigDecimal currentBudgetBalance) {
        this.currentBudgetBalance = currentBudgetBalance;
    }

    public AccountAndBudgetDto withCurrentBudgetBalance(BigDecimal currentBudgetBalance) {
        setMaxBudgetBalance(currentBudgetBalance);
        return this;
    }

    public BigDecimal getMinimumAlert() {
        return minimumAlert;
    }

    public void setMinimumAlert(BigDecimal minimumAlert) {
        this.minimumAlert = minimumAlert;
    }

    public AccountAndBudgetDto withMinimumAlert(BigDecimal minimumAlert) {
        setMinimumAlert(minimumAlert);
        return this;
    }

    public BigDecimal getAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(BigDecimal autoCancel) {
        this.autoCancel = autoCancel;
    }

    public AccountAndBudgetDto withAutoCancel(BigDecimal autoCancel) {
        setAutoCancel(autoCancel);
        return this;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public AccountAndBudgetDto withBudgetName(String budgetName) {
        setBudgetName(budgetName);
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountAndBudgetDto withAccountId(String accountId) {
        setAccountId(accountId);
        return this;
    }


}
