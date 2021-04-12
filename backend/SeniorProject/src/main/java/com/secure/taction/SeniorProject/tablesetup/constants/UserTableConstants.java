package com.secure.taction.SeniorProject.tablesetup.constants;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class UserTableConstants {
    
    public static String USER_TABLE_NAME = "ST_USER";
    public static String USER_ID = "USER_ID";
    public static String USER_NAME = "USER_NAME";
    public static String EMAIL = "EMAIL";
    public static String PASSWORD = "PASSWORD";
    public static String PHONE_NUMBER = "PHONE_NUMBER";
    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";
    public static String CARDS = "CARDS";

    private static List<KeySchemaElement> USER_KEY_SCHEMA = 
        Arrays.asList(
            new KeySchemaElement()
                .withAttributeName(USER_ID)
                .withKeyType(KeyType.HASH),
            new KeySchemaElement()
                .withAttributeName(USER_NAME)
                .withKeyType(KeyType.RANGE)
        );
    
    private static List<AttributeDefinition> USER_ATTRIBUTE_DEFINITIONS =
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(USER_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(USER_NAME)
                .withAttributeType("S")
        );
    
    private static List<AttributeDefinition> USER_ALL_ATTRIBUTES = 
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName(USER_ID)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(USER_NAME)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(EMAIL)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(PASSWORD)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(PHONE_NUMBER)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(FIRST_NAME)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(LAST_NAME)
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName(CARDS)
                .withAttributeType("S")
    );

    private static ProvisionedThroughput USER_TABLE_THROUGHPUT = new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L);

    public static List<KeySchemaElement> getUserKeySchema() {return USER_KEY_SCHEMA;}
    public static List<AttributeDefinition> getUserAttributeDefinitions() {return USER_ATTRIBUTE_DEFINITIONS;}
    public static List<AttributeDefinition> getUserAllAttributeDefinitions() {return USER_ALL_ATTRIBUTES;}
    public static ProvisionedThroughput getUserProvisionedThroughPut() {return USER_TABLE_THROUGHPUT;}
}
