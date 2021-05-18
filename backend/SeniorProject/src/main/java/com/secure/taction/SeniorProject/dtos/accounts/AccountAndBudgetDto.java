package com.secure.taction.SeniorProject.dtos.accounts;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;

public class AccountAndBudgetDto {

    private String accountName;
    private BigDecimal accountBalance;
    private BudgetDto budgetDto;

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

    public BudgetDto getBudgetDto() {
        return budgetDto;
    }

    public void setBudgetDto(BudgetDto budgetDto) {
        this.budgetDto = budgetDto;
    }

    public AccountAndBudgetDto withBudgetDto(BudgetDto budgetDto) {
        setBudgetDto(budgetDto);
        return this;
    }

}
