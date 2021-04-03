package com.secure.taction.SeniorProject.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.secure.taction.SeniorProject.constants.IamCredentials;

public class DynamoClientUtil {
    
    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(IamCredentials.awsCreds))
            .withRegion(Regions.US_EAST_1)
            .build();

    static DynamoDB dynamoDB = new DynamoDB(client);

    public static DynamoDB getClient() {
        return dynamoDB;
    }
}
