package com.secure.taction.SeniorProject.dtos.budget;

import java.math.BigDecimal;

public class BudgetDto {

    private String budgetId;
    private String userId;
    private String cardId;
    private BigDecimal maxBudgetBalance;
    private BigDecimal currentBudgetBalance;
    private BigDecimal minimumAlert;
    private BigDecimal autoCancel;
    private String budgetName;

    public BudgetDto() {
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public BudgetDto withBudgetId(String budgetId) {
        setBudgetId(budgetId);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BudgetDto withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BudgetDto withCardId(String cardId) {
        setCardId(cardId);
        return this;
    }

    public BigDecimal getMaxBudgetBalance() {
        return maxBudgetBalance;
    }

    public void setMaxBudgetBalance(BigDecimal maxBudgetBalance) {
        this.maxBudgetBalance = maxBudgetBalance;
    }

    public BudgetDto withMaxBudgetBalance(BigDecimal maxBudgetBalance) {
        setMaxBudgetBalance(maxBudgetBalance);
        return this;
    }

    public BigDecimal getCurrentBudgetBalance() {
        return currentBudgetBalance;
    }

    public void setCurrentBudgetBalance(BigDecimal currentBudgetBalance) {
        this.currentBudgetBalance = currentBudgetBalance;
    }

    public BudgetDto withCurrentBudgetBalance(BigDecimal currentBudgetBalance) {
        setCurrentBudgetBalance(currentBudgetBalance);
        return this;
    }

    public BigDecimal getMinimumAlert() {
        return minimumAlert;
    }

    public void setMinimumAlert(BigDecimal minimumAlert) {
        this.minimumAlert = minimumAlert;
    }

    public BudgetDto withMinimumAlert(BigDecimal minimumAlert) {
        setMinimumAlert(minimumAlert);
        return this;
    }

    public BigDecimal getAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(BigDecimal autoCancel) {
        this.autoCancel = autoCancel;
    }

    public BudgetDto withAutoCancel(BigDecimal autoCancel) {
        setAutoCancel(autoCancel);
        return this;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public BudgetDto withBudgetName(String budgetName) {
        setBudgetName(budgetName);
        return this;
    }


}
