package com.secure.taction.SeniorProject.models;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;

public class UserCredentials {

    private Item credentialsItem;
    public UserCredentials() {}

    public Item getItem() {return credentialsItem; }

    public void setItem(Item credentialsItem) { this.credentialsItem = credentialsItem;}

    public UserCredentials withItem(Item credentialsItem) {
        setItem(credentialsItem);
        return this;
    }

    public Map<String, Object> getAttributes() {
        return credentialsItem.asMap();
    }
    
}
