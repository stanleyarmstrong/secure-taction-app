package com.secure.taction.SeniorProject.tablesetup.services;

import java.util.Iterator;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.secure.taction.SeniorProject.tablesetup.constants.CreditCardTableConstants;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

import org.springframework.stereotype.Service;

@Service
public class CreditCardTableService {
    
    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder
                .EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();
    static DynamoDB dynamoDB = new DynamoDB(client);

    public String createTable() {

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(CreditCardTableConstants.CREDIT_CARD_TABLE_NAME)
                .withKeySchema(CreditCardTableConstants.getCardSchemaElements())
                .withAttributeDefinitions(CreditCardTableConstants.getCardAttributeDefinitions())
                .withProvisionedThroughput(CreditCardTableConstants.getCardProvisionedThroughput());

        Table table = dynamoDB.createTable(request);

        try {
            table.waitForActive();
        } catch (Exception e) {
            return e.getMessage();
        }

        TableDescription tableDescription = dynamoDB
                                                .getTable(CreditCardTableConstants.CREDIT_CARD_TABLE_NAME)
                                                .describe();
        String resultString = String.format("%s: %s \t ReadCapacityUnits: %d \t WriteCapacityUnits: %d",
            tableDescription.getTableStatus(),
            tableDescription.getTableName(),
            tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
            tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
        return resultString;
    }
}
