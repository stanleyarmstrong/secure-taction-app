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


public class CreditCardTableConstants {
    
    public static String CREDIT_CARD_TABLE_NAME = "ST_CREDIT_CARDS";
    public static String CARD_ID = "CARD_ID";
    public static String CARD_NAME = "CARD_NAME";

    private static List<KeySchemaElement> CARD_KEY_SCHEMA =
        Arrays.asList(
            new KeySchemaElement()
                .withAttributeName(CARD_ID)
                .withKeyType(KeyType.HASH),
            new KeySchemaElement()
                .withAttributeName(CARD_NAME)
                .withKeyType(KeyType.RANGE)
        );
    
    private static List<AttributeDefinition> CARD_ATTRIBUTE_DEFINITIONS = 
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(CARD_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(CARD_NAME)
                .withAttributeType("S")
        );

    private static ProvisionedThroughput CARD_TABLE_THROUGHPUT = new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L);

    public static List<KeySchemaElement> getCardSchemaElements() {return CARD_KEY_SCHEMA;}
    public static List<AttributeDefinition> getCardAttributeDefinitions() {return CARD_ATTRIBUTE_DEFINITIONS;}
    public static ProvisionedThroughput getCardProvisionedThroughput() {return CARD_TABLE_THROUGHPUT;}
}
