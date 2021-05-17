package com.secure.taction.SeniorProject.dtos.accounts;

import java.util.LinkedList;
import java.util.List;

import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;

public class AccountAndBudgetDto {

    private AccountDto accountDto;
    private List<BudgetDto> budgetDtos = new LinkedList<>();

    public AccountAndBudgetDto() {
    }

    public AccountDto getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public AccountAndBudgetDto withAccountDto(AccountDto accountDto) {
        setAccountDto(accountDto);
        return this;
    }

    public List<BudgetDto> getBudgetDtos() {
        return budgetDtos;
    }

    public void setBudgetDtos(List<BudgetDto> budgetDtos) {
        this.budgetDtos = budgetDtos;
    }

    public AccountAndBudgetDto withBudgetDtos(List<BudgetDto> budgetDtos) {
        setBudgetDtos(budgetDtos);
        return this;
    }

    public AccountAndBudgetDto addBudgetDto(BudgetDto budgetDto) {
        if (this.budgetDtos == null) {
            this.budgetDtos = new LinkedList<>();
        }
        this.budgetDtos.add(budgetDto);
        return this;
    }

}
