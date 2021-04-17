package com.secure.taction.SeniorProject.tablesetup.services;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import com.secure.taction.SeniorProject.tablesetup.constants.AccountTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;

import org.springframework.stereotype.Service;

@Service
public class AccountTableService {
    
    DynamoDB dynamoDB = DynamoClientUtil.getClient();   

    public String createTable() {

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(AccountTableConstants.ACCOUNT_TABLE_NAME)
                .withKeySchema(AccountTableConstants.getCardSchemaElements())
                .withAttributeDefinitions(AccountTableConstants.getCardAttributeDefinitions())
                .withProvisionedThroughput(AccountTableConstants.getCardProvisionedThroughput());

        Table table = dynamoDB.createTable(request);

        try {
            table.waitForActive();
        } catch (Exception e) {
            return e.getMessage();
        }

        TableDescription tableDescription = dynamoDB
                                                .getTable(AccountTableConstants.ACCOUNT_TABLE_NAME)
                                                .describe();
        String resultString = String.format("%s: %s \t ReadCapacityUnits: %d \t WriteCapacityUnits: %d",
            tableDescription.getTableStatus(),
            tableDescription.getTableName(),
            tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
            tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
        return resultString;
    }

    public String deleteTable() {
        Table table = dynamoDB.getTable(AccountTableConstants.ACCOUNT_TABLE_NAME);
        try {
            System.out.println("Issuing DeleteTable request");
            table.delete();

            System.out.println("Waiting for " + AccountTableConstants.ACCOUNT_TABLE_NAME + "...");
            table.waitForDelete();
            return "Table: " + AccountTableConstants.ACCOUNT_TABLE_NAME + "successfully deleted";
        } catch (Exception e) {
            System.err.println("DeleteTable request failed");
            return e.getMessage();
        }
    }
}
