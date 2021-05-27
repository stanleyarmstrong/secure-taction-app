package com.secure.taction.SeniorProject.utils;

import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.secure.taction.SeniorProject.controllers.AccountController;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.TransactionTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

public class QueryUtils {

    public static QuerySpec transactionQuerySpec(String accountId) {
        return new QuerySpec()
            .withKeyConditionExpression(TransactionTableConstants.ACCOUNT_ID + " = :accountId")
            .withValueMap(new ValueMap()
                .withString(":accountId", accountId)
            ).withConsistentRead(true);
    }

    public static QuerySpec userQuerySpec(String userId) {
        return new QuerySpec()
            .withKeyConditionExpression(UserTableConstants.USER_ID + " = :userId")
            .withValueMap(new ValueMap()
                .withString(":userId", userId)
            ).withConsistentRead(true);
    }

    public static QuerySpec accountQuerySpec(String accountId) { 
        return new QuerySpec()
            .withKeyConditionExpression(AccountTableConstants.ACCOUNT_ID +" = :accountId")
            .withValueMap(new ValueMap()
                .withString(":accountId", accountId)
            ).withConsistentRead(true);
    }

    public static QuerySpec budgetQuerySpec(String budgetId) {
        return new QuerySpec()
            .withKeyConditionExpression(BudgetTableConstants.BUDGET_ID +" = :budgetId")
            .withValueMap(new ValueMap()
                .withString(":budgetId", budgetId)
            ).withConsistentRead(true);
    }
}
