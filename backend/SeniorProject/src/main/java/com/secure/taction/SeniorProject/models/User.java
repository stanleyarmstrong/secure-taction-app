package com.secure.taction.SeniorProject.models;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.secure.taction.SeniorProject.tablesetup.constants.UserTableConstants;

public class User {

    private Item userItem;
    // debug value
    public User() {}

    public Item getItem() { return userItem;}

    public void setItem(Item userItem) { this.userItem = userItem;}

    public User withItem(Item userItem) { 
        setItem(userItem);
        return this;
    }

    public Map<String, Object> getAttributes() {
        return userItem.asMap();
    }

    // Debugging method
    public User withUserId(String userId) {
        userItem.withPrimaryKey(UserTableConstants.USER_ID, userId);
        return this;
    }

}
