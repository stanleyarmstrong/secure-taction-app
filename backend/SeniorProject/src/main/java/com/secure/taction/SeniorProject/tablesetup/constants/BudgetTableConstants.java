package com.secure.taction.SeniorProject.tablesetup.constants;


import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class BudgetTableConstants {

    public  static String BUDGET_TABLE_NAME = "ST_BUDGET";
    public static String BUDGET_ID = "BUDGET_ID";
    public static String USER_ID = "USER_ID";
    public static String ACCOUNT_ID = "CARD_ID";
    public static String MAX_BALANCE = "MAX_BALANCE";
    public static String CUR_BALANCE = "CUR_BALANCE";
    public static String MIN_ALERT = "MIN_ALERT";
    public static String AUTO_CANCEL = "AUTO_CANCEL";
    public static String BUDGET_NAME = "BUDGET_NAME";

    private static List<KeySchemaElement> BUDGET_KEY_SCHEMA = 
        Arrays.asList(
            new KeySchemaElement()
                .withAttributeName(BUDGET_ID)
                .withKeyType(KeyType.HASH),
            new KeySchemaElement()
                .withAttributeName(USER_ID)
                .withKeyType(KeyType.RANGE)
        );

    private static List<AttributeDefinition> BUDGET_KEY_ATTRIBUTES = 
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(BUDGET_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(USER_ID)
                .withAttributeType("S")
        );

    private static List<AttributeDefinition> BUDGET_ATTRIBUTE_DEFINITIONS =
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(BUDGET_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(USER_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(ACCOUNT_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(MAX_BALANCE)
                .withAttributeType("N"),
            new AttributeDefinition()
                .withAttributeName(CUR_BALANCE)
                .withAttributeType("N"),
            new AttributeDefinition()
                .withAttributeName(MIN_ALERT)
                .withAttributeType("N"),
            new AttributeDefinition()
                .withAttributeName(AUTO_CANCEL)
                .withAttributeType("N"),
            new AttributeDefinition()
                .withAttributeName(BUDGET_NAME)
                .withAttributeType("S")
        );
    
    private static ProvisionedThroughput BUDGET_TABLE_THROUGHPUT = new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L);
    
    public static List<KeySchemaElement> getBudgetKeySchema() {return BUDGET_KEY_SCHEMA;}
    public static List<AttributeDefinition> getBudgetKeyAttributes() {return BUDGET_KEY_ATTRIBUTES;}
    public static List<AttributeDefinition> getBudgetAttributeDefinitions() {return BUDGET_ATTRIBUTE_DEFINITIONS;}
    public static ProvisionedThroughput getBudgetProvisionedThroughPut() {return BUDGET_TABLE_THROUGHPUT;}
}
