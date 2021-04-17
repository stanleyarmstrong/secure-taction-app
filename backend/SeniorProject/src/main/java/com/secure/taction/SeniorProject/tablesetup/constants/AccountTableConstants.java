package com.secure.taction.SeniorProject.tablesetup.constants;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

/*
*
*
*   STUB-CLASS: Need to discuss the specifications
*   of the cards again. "Handled by plaid" is a bad description
*
*/


public class AccountTableConstants {
    
    public static String ACCOUNT_TABLE_NAME = "ST_ACCOUNTS";
    public static String ACCOUNT_ID = "ACCOUNT_ID";
    public static String USER_ID = "USER_ID";
    public static String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public static String BALANCE = "BALANCE";
    public static String ACCOUNT_NAME = "ACCOUNT_NAME";

    private static List<KeySchemaElement> ACCOUNT_KEY_SCHEMA =
        Arrays.asList(
            new KeySchemaElement()
                .withAttributeName(ACCOUNT_ID)
                .withKeyType(KeyType.HASH),
            new KeySchemaElement()
                .withAttributeName(USER_ID)
                .withKeyType(KeyType.RANGE)
        );
    
    private static List<AttributeDefinition> ACCOUNT_ATTRIBUTE_DEFINITIONS = 
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(ACCOUNT_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(USER_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(ACCOUNT_TYPE)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(BALANCE)
                .withAttributeType("N"),
            new AttributeDefinition()
                .withAttributeName(ACCOUNT_NAME)
                .withAttributeType("S")
        );

    private static ProvisionedThroughput ACCOUNT_TABLE_THROUGHPUT = new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L);

    public static List<KeySchemaElement> getCardSchemaElements() {return ACCOUNT_KEY_SCHEMA;}
    public static List<AttributeDefinition> getCardAttributeDefinitions() {return ACCOUNT_ATTRIBUTE_DEFINITIONS;}
    public static ProvisionedThroughput getCardProvisionedThroughput() {return ACCOUNT_TABLE_THROUGHPUT;}
}