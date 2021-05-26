package com.secure.taction.SeniorProject.models;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

public class Budget {

    private Item budgetItem;
    // debug value
    public Budget() {}

    public Item getItem() { return budgetItem;}

    public void setItem(Item budgetItem) { this.budgetItem = budgetItem;}

    public Budget withItem(Item budgetItem) { 
        setItem(budgetItem);
        return this;
    }

    public Map<String, Object> getAttributes() {
        return budgetItem.asMap();
    }

    // Debugging method
    public Budget withBudgetId(String budgetId) {
        budgetItem.withPrimaryKey(BudgetTableConstants.BUDGET_ID, budgetId);
        return this;
    }
}
