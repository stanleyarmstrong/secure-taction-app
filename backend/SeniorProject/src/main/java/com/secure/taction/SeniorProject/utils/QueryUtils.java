package com.secure.taction.SeniorProject.utils;

import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

public class QueryUtils {
    public static QuerySpec userQuerySpec(String userId) {
        return new QuerySpec()
            .withKeyConditionExpression(UserTableConstants.USER_ID + " = :userId")
            .withValueMap(new ValueMap()
                .withString(":userId", userId)
            ).withConsistentRead(true);
    }

    public static QuerySpec accountQuerySpec(String userId) { 
        return new QuerySpec()
            .withKeyConditionExpression(AccountTableConstants.USER_ID +" = :userId")
            .withValueMap(new ValueMap()
                .withString(":userId", userId)
            ).withConsistentRead(true);
    }

    public static QuerySpec budgetQuerySpec(String accountId) {
        return new QuerySpec()
            .withKeyConditionExpression(BudgetTableConstants.ACCOUNT_ID +" = :accountId")
            .withValueMap(new ValueMap()
                .withString(":accountId", accountId)
            ).withConsistentRead(true);
    }
}
