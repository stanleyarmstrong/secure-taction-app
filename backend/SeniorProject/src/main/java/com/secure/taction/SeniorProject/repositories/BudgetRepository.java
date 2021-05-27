package com.secure.taction.SeniorProject.repositories;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.secure.taction.SeniorProject.dtos.budget.BudgetDto;
import com.secure.taction.SeniorProject.models.Budget;
import com.secure.taction.SeniorProject.tablesetup.constants.BudgetTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;
import com.secure.taction.SeniorProject.utils.SnsClientUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BudgetRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(BudgetRepository.class);
    private static String cardId = ":cardId";
    private static String maxBudgetBalance = ":maxBudgetBalance";
    private static String currentBudgetBalance = ":currentBudgetBalance";
    private static String minimumAlert = ":minimumAlert";
    private static String autoCancel = ":autoCancel";
    private static String budgetName = ":budgetName";

    private static String updateExpression = 
        "set\n" +
        BudgetTableConstants.ACCOUNT_ID + " = " + cardId + ",\n" +
        BudgetTableConstants.MAX_BALANCE + " = " + maxBudgetBalance + ",\n" +
        BudgetTableConstants.CUR_BALANCE + " = " + currentBudgetBalance + ",\n" +
        BudgetTableConstants.MIN_ALERT + " = " + minimumAlert + ",\n" +
        BudgetTableConstants.AUTO_CANCEL + " = " + autoCancel + ",\n" +
        BudgetTableConstants.BUDGET_NAME + " = " + budgetName;

    DynamoDB dynamoDB = DynamoClientUtil.getDynamoClient();
    Table table = dynamoDB.getTable(BudgetTableConstants.BUDGET_TABLE_NAME);

    public Budget findByIdAndUserId(GetItemSpec spec) throws Exception {
        Item outcome = table.getItem(spec);
        return new Budget().withItem(outcome);
    }

    public Budget save(Budget budget) {
        try {
            PutItemOutcome outcome = table.putItem(budget.getItem());
            if (Objects.nonNull(outcome)) {
                SnsClientUtil.budgetCreate();
                return budget;
            }
            else
                return null;
        } catch (Exception e) {
            LOGGER.error("Exception occured while adding record to the Budget Table: ", e);
            return null;
        }
    }

    public Budget update(BudgetDto budgetDto) {
        UpdateItemSpec updateItemSpec = 
            new UpdateItemSpec()
                .withPrimaryKey(
                    BudgetTableConstants.BUDGET_ID, budgetDto.getBudgetId(),
                    BudgetTableConstants.USER_ID, budgetDto.getUserId())
                .withUpdateExpression(updateExpression)
                .withValueMap(new ValueMap()
                    .withString(cardId, budgetDto.getAccountId())
                    .withString(budgetName, budgetDto.getBudgetName())
                    .withNumber(maxBudgetBalance, budgetDto.getMaxBudgetBalance())
                    .withNumber(currentBudgetBalance, budgetDto.getCurrentBudgetBalance())
                    .withNumber(autoCancel, budgetDto.getAutoCancel())
                    .withNumber(minimumAlert, budgetDto.getMinimumAlert()))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        try {
            Item outcome = table.updateItem(updateItemSpec).getItem();
            if (Objects.nonNull(outcome)) {
                return new Budget().withItem(outcome);
            } else {
                System.err.println("Unable to update budget with Id: " + budgetDto.getBudgetId());
                return null;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public void deleteByIdAndUserId(DeleteItemSpec spec) throws Exception {
        SnsClientUtil.budgetDelete();
        table.deleteItem(spec);
    }

    public ItemCollection<QueryOutcome> queryForBudget(QuerySpec budgetQuerySpec) {
        return table.query(budgetQuerySpec);
    }
    
}
