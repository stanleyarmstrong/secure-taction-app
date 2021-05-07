package com.secure.taction.SeniorProject.tablesetup.constants;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class TransactionTableConstants {

    public static String TRANSACTION_TABLE_NAME = "ST_TRANSACTION";
    public static String TRANSACTION_ID = "TRANSACTION_ID";
    public static String ACCOUNT_ID = "ACCOUNT_ID";
    public static String AMOUNT = "AMOUNT";
    public static String ADDRESS = "ADDRESS";
    public static String VENDOR = "VENDOR";
    public static String CATEGORIES = "CATEGORIES";
    
    private static List<KeySchemaElement> TRANSACTION_KEY_SCHEMA = 
        Arrays.asList(
            new KeySchemaElement()
                .withAttributeName(TRANSACTION_ID)
                .withKeyType(KeyType.HASH),
            new KeySchemaElement()
                .withAttributeName(ACCOUNT_ID)
                .withKeyType(KeyType.RANGE)
    );

    private static List<AttributeDefinition> TRANSACTION_KEY_ATTRIBUTES = 
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(TRANSACTION_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(ACCOUNT_ID)
                .withAttributeType("S")
    );

    private static ProvisionedThroughput TRANSACTION_TABLE_THROUGHPUT = new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L);
    
    public static ProvisionedThroughput getTransactionProvisionedThroughPut() {return TRANSACTION_TABLE_THROUGHPUT;}
    public static List<KeySchemaElement> getTransactionKeySchema() {return TRANSACTION_KEY_SCHEMA;}
    public static List<AttributeDefinition> getTransactionKeyAttributes() {return TRANSACTION_KEY_ATTRIBUTES;}
}
