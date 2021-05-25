package com.secure.taction.SeniorProject.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.secure.taction.SeniorProject.constants.IamCredentials;

public class SnsClientUtil {

    public static String BANK_ACCOUNT_ARN = "arn:aws:sns:us-east-1:200257006159:Bank_Account_Request";
    public static String USER_UPDATE_ARN = "";
    public static String BUDGET_CREATE_ARN = "";
    public static String BUDGET_DELETE_ARN = "";
    public static String BANK_ACCOUNT_DELETE_ARN = "";
    public static String HELLO_ARN = "";

    private static String TEST_TOPIC = "TEST EMAIL INFORMATION, THIS WILL COST ME 1 CENT LMAO";
    private static String TEST_MESSAGE = "THIS IS A TEST EMAIL";
    private static String BANK_ACCOUNT_ACCESS_TOPIC = "New Bank Account Access";
    private static String BANK_ACCOUNT_ACCESS_MESSAGE = 
            "A request to access your bank account was approved by Plaid.\n" + 
            "If this was you, you may ignore this message. If this wasn't you" +
            "we recommend send an email to secure@taction.com, or call (123)456.7890 immediately";

    private static String EMAIL = "biglesia@calpoly.edu";

    
    private static AmazonSNSClient snsClient = (AmazonSNSClient) AmazonSNSClientBuilder
            .standard()
            .build();

    public static AmazonSNSClient getSnsClient() {
        return snsClient;
    }

    public static void testCall() {
        String message = "Test message for senior project";
        String email = "biglesia@calpoly.edu";

        snsClient.publish(HELLO_ARN, TEST_MESSAGE, TEST_TOPIC);
    }

    public static void bankAccountAccess() {
        snsClient.publish(BANK_ACCOUNT_ARN, BANK_ACCOUNT_ACCESS_MESSAGE, BANK_ACCOUNT_ACCESS_TOPIC);
    }
}

