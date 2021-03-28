package com.secure.taction.SeniorProject.tablesetup.constants;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class UserTableConstants {
    
    public static String USER_TABLE_NAME = "ST_USER";
    
    private static List<AttributeDefinition> USER_ATTRIBUTES = 
        Arrays.asList(
            new AttributeDefinition()
                .withAttributeName("USER_ID")
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName("USER_NAME")
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName("EMAIL")
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName("PASSWORD")
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName("PHONE_NUMBER")
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName("FIRST_NAME")
                .withAttributeType("S"),
            new AttributeDefinition()
                .withAttributeName("LAST_NAME")
                .withAttributeType("S")
    );
    private static List<KeySchemaElement> USER_SCHEMA_ELEMENTS = 
        Arrays.asList(
            new KeySchemaElement()
                .withAttributeName("USER_ID")
                .withKeyType("HASH"),
            new KeySchemaElement()
                .withAttributeName("USER_NAME")
                .withKeyType("S"),
            new KeySchemaElement()
                .withAttributeName("EMAIL")
                .withKeyType("S"),
            new KeySchemaElement()
                .withAttributeName("PASSWORD")
                .withKeyType("S"),
            new KeySchemaElement()
                .withAttributeName("PHONE_NUMBER")
                .withKeyType("S"),
            new KeySchemaElement()
                .withAttributeName("FIRST_NAME")
                .withKeyType("S"),
            new KeySchemaElement()
                .withAttributeName("LAST_NAME")
                .withKeyType("S")
    );
    private static ProvisionedThroughput USER_TABLE_THROUGHPUT = new ProvisionedThroughput()
                    .withReadCapacityUnits(5L)
                    .withWriteCapacityUnits(6L);

    public static List<AttributeDefinition> getUserAttributeDefinitions() {return USER_ATTRIBUTES;}
    public static List<KeySchemaElement> getUserSchemaElements() {return USER_SCHEMA_ELEMENTS;}
    public static ProvisionedThroughput getUserProvisionedThroughPut() {return USER_TABLE_THROUGHPUT;}
}
