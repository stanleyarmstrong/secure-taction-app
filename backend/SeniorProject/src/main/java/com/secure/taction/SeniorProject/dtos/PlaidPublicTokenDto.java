package com.secure.taction.SeniorProject.dtos;

public class PlaidPublicTokenDto {
    private String publicToken;
    private String userId;
    private String userName;

    public PlaidPublicTokenDto() {
    }

    public String getPublicToken() {
        return publicToken;
    }
    
    public void setPublicToken(String publicToken) {
        this.publicToken = publicToken;
    }
    
    public PlaidPublicTokenDto withPublicToken(String publicToken) {
        setPublicToken(publicToken);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PlaidPublicTokenDto withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }    

    public PlaidPublicTokenDto withUserName(String userName) {
        setUserName(userName);
        return this;
    }

}
