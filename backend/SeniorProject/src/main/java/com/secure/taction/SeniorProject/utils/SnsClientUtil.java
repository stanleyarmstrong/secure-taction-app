package com.secure.taction.SeniorProject.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.secure.taction.SeniorProject.constants.IamCredentials;

public class SnsClientUtil {


    public static String TEST_ACCOUNT_ID = "access-sandbox-491b52d1-245b-4c22-af99-bdb62c1ca7a2";

    private static String EMAIL = "biglesia@calpoly.edu";

    public static String BANK_ACCOUNT_ARN = "arn:aws:sns:us-east-1:200257006159:Bank_Account_Request";
    public static String USER_UPDATE_ARN = "";
    public static String BUDGET_CREATE_ARN = "arn:aws:sns:us-east-1:200257006159:Budget_Create_Topic";
    public static String BUDGET_DELETE_ARN = "arn:aws:sns:us-east-1:200257006159:Budget_Delete_Topic";
    public static String BANK_DELETE_ARN = "arn:aws:sns:us-east-1:200257006159:Budget_Delete_Topic";
    public static String HELLO_ARN = "arn:aws:sns:us-east-1:200257006159:HelloTopic";

    private static String ENDER = 
            "If this was you, you may ignore this message. If this wasn't you " +
            "we recommend send an email to secure@taction.com, or call (123)456.7890 immediately";

    private static String TEST_TOPIC = "Transaction Alert";
    private static String TEST_MESSAGE = 
        "A transaction was made with your account at:\n" +
        "123 Location Pl, San Luis Obispo, CA, 93405\n" +
        "Type: in-person\n" +
        "Amount: 150.44\n" + 
        "Vendor: Apple store\n\n" +
        ENDER;

    private static String BANK_ACCOUNT_ACCESS_TOPIC = "New Bank Account Access";
    private static String BANK_ACCOUNT_ACCESS_MESSAGE = 
            "A request to access your bank account was approved by Plaid.\n" + 
            ENDER;

    private static String BANK_ACCOUNT_DELETE_TOPIC = "Account Delete Notification";
    private static String BANK_ACCOUNT_DELETE_MESSAGE = 
            "A request to delete your bank information from the App was made\n" + 
            ENDER;

    private static String BUDGET_CREATE_TOPIC = "New Budget Notification";
    private static String BUDGET_CREATE_MESSAGE = 
        "A new budget made with your account.\n\n" + ENDER;

    private static String BUDGET_DELETE_TOPIC = "Delete Budget Notification";
    private static String BUDGET_DELETE_MESSAGE = 
        "A budget was deleted from your account recently.\n\n" + ENDER;

    
    private static AmazonSNSClient snsClient = (AmazonSNSClient) AmazonSNSClientBuilder
            .standard()
            .build();

    public static AmazonSNSClient getSnsClient() {
        return snsClient;
    }

    public static void testCall() {
        snsClient.publish(HELLO_ARN, TEST_MESSAGE, TEST_TOPIC);
    }

    public static void bankAccountAccess() {
        snsClient.publish(BANK_ACCOUNT_ARN, BANK_ACCOUNT_ACCESS_MESSAGE, BANK_ACCOUNT_ACCESS_TOPIC);
    }

    public static void bankAccountDelete() {
        snsClient.publish(BANK_DELETE_ARN, BANK_ACCOUNT_DELETE_MESSAGE, BANK_ACCOUNT_DELETE_TOPIC);
    }

    public static void budgetCreate() {
        snsClient.publish(BUDGET_CREATE_ARN, BUDGET_CREATE_MESSAGE, BUDGET_CREATE_TOPIC);
    }

    public static void budgetDelete() {
        snsClient.publish(BUDGET_DELETE_ARN, BUDGET_DELETE_MESSAGE, BUDGET_DELETE_TOPIC);
    }

}

