package com.secure.taction.SeniorProject.utils;

import com.plaid.client.PlaidClient;

import okhttp3.logging.HttpLoggingInterceptor;

public class PlaidClientUtil {

    public static String CLIENT_ID = "601ccb4f00e80f00113c58f5";
    public static String SANDBOX_KEY = "e5ac68ca184449a580045975de6533";   

    public static PlaidClient getPlaidClient() {
        return PlaidClient.newBuilder() 
            .clientIdAndSecret(CLIENT_ID, SANDBOX_KEY)
            .sandboxBaseUrl()
            .logLevel(HttpLoggingInterceptor.Level.BODY)
            .build();
    }
}
