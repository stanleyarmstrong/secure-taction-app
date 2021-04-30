package com.secure.taction.SeniorProject.dtos;

import org.springframework.stereotype.Service;

public class PlaidAuthDto {

    private String accessToken;
    private String itemId;
    
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public PlaidAuthDto withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public PlaidAuthDto withItemId(String itemId) {
        withItemId(itemId);
        return this;
    }

}
