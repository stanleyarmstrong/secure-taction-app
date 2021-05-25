package com.secure.taction.SeniorProject.controllers;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.secure.taction.SeniorProject.utils.SnsClientUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String test() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/SNS", method = RequestMethod.GET) 
    public String SnsTest() {
        SnsClientUtil.testCall();
        return "return from testing";
    }

}
