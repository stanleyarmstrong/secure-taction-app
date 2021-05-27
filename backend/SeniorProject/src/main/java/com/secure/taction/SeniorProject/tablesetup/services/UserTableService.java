package com.secure.taction.SeniorProject.tablesetup.services;

import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;
import com.secure.taction.SeniorProject.utils.DynamoClientUtil;

import org.springframework.stereotype.Service;

@Service
public class UserTableService {

    DynamoDB dynamoDB = DynamoClientUtil.getDynamoClient();

    public String createTable() {

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(UserTableConstants.USER_TABLE_NAME)
                .withKeySchema(UserTableConstants.getUserKeySchema())
                .withAttributeDefinitions(UserTableConstants.getUserAttributeDefinitions())
                .withProvisionedThroughput(UserTableConstants.getUserProvisionedThroughPut());

        Table table = dynamoDB.createTable(request);

        try {
            table.waitForActive();
        } catch (Exception e) {
            return e.getMessage();
        }

        TableDescription tableDescription = dynamoDB
                                                .getTable(UserTableConstants.USER_TABLE_NAME)
                                                .describe();
        String resultString = String.format("%s: %s \t ReadCapacityUnits: %d \t WriteCapacityUnits: %d",
            tableDescription.getTableStatus(),
            tableDescription.getTableName(),
            tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
            tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
        return resultString;
    }

    public void listTables() {
        TableCollection<ListTablesResult> tables = dynamoDB.listTables();
        Iterator<Table> iterator = tables.iterator();
        String names = "";
        System.out.println("Listing table names");

        while (iterator.hasNext()) {
            Table table = iterator.next();
            System.out.println(table.getTableName());
        }
    }

    public String deleteTable() {
        Table table = dynamoDB.getTable(UserTableConstants.USER_TABLE_NAME);
        try {
            System.out.println("Issuing DeleteTable request");
            table.delete();

            System.out.println("Waiting for " + UserTableConstants.USER_TABLE_NAME + "...");
            table.waitForDelete();
            return "Table: " + UserTableConstants.USER_TABLE_NAME + "successfully deleted";
        } catch (Exception e) {
            System.err.println("DeleteTable request failed");
            return e.getMessage();
        }
    }
}
