package com.secure.taction.SeniorProject.utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.secure.taction.SeniorProject.constants.IamCredentials;

public class SnsClientUtil {
    
    private static AmazonSNSClient snsClient = (AmazonSNSClient) AmazonSNSClientBuilder
            .standard()
            .build();

    public static AmazonSNSClient getSnsClient() {
        return snsClient;
    }
}
